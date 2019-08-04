package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("customUser")
	public User customUser(@AuthenticationPrincipal CurrentUser currentUser) {
		User customUser = currentUser.getUser();
		return customUser;
	}

	@ModelAttribute("users")
	public List<User> users() {
		return userRepository.findUsersByRoles(roleRepository.findByRole("ROLE_USER"));
	}

	@ModelAttribute("admins")
	public List<User> admins() {
		return userRepository.findUsersByRoles(roleRepository.findByRole("ROLE_ADMIN"));
	}

	@ModelAttribute("institutionsList")
	public List<Institution> institutionsList() {
		return institutionRepository.findAll();
	}

	@GetMapping
	public String index() {
		return "admin/index";
	}

	@GetMapping("/userInfo")
	public String userInfo() {
		return "admin/informationUser";
	}

	@GetMapping("/adminInfo")
	public String adminInfo() {
		return "admin/informationAdmin";
	}

	@GetMapping("/institutionInfo")
	public String institutionInfo() {
		return "admin/informationInstitution";
	}


	@GetMapping("/userDelete/{userId}")
	public String userDelete(@PathVariable Long userId) {
		userRepository.delete(userRepository.getOne(userId));
		return "admin/index";
	}

	@GetMapping("/userBlock/{userId}")
	public String userBlock(@PathVariable Long userId) {
		userRepository.updateUserSetEnabled(userId, 0);
		return "admin/index";
	}

	@GetMapping("/userUnBlock/{userId}")
	public String userUnBlock(@PathVariable Long userId) {
		userRepository.updateUserSetEnabled(userId, 1);
		return "admin/index";
	}

	@GetMapping("/userEdit/{userId}")
	public String userEdit(@PathVariable Long userId, Model model) {
		model.addAttribute("userEdit", userRepository.getOne(userId));
		return "admin/userEdit";
	}

	@PostMapping("/userEdit/{userId}")
	public String userEdit(@Valid @ModelAttribute("userEdit") User userEdit, BindingResult result, @PathVariable Long userId, Model model) {
		if (result.hasErrors()) {
			return "admin/userEdit";
		}
		User userExists = userRepository.findByEmail(userEdit.getEmail());
		if (userExists != null) {
			model.addAttribute("userExists", "There is already a user registered with the email provided");
			return "admin/userEdit";
		}
		userEdit.setPassword(passwordEncoder.encode(userEdit.getPassword()));
		userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPasswordAAndEnabled(userId, userEdit.getFirstName(), userEdit.getLastName(), userEdit.getEmail(), userEdit.getPassword());
		return "admin/index";
	}


	@GetMapping("/institutionAdd")
	public String institutionAdd(Model model) {
		model.addAttribute("institution", new Institution());
		return "admin/institutionAdd";
	}

	@PostMapping("/institutionAdd")
	public String institutionAdd(@Valid @ModelAttribute("institution") Institution institution, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/institutionAdd";
		}
		institutionRepository.save(institution);
		return "admin/index";
	}

	@GetMapping("/institutionDelete/{institutionId}")
	public String institutionDelete(@PathVariable Long institutionId) {
		institutionRepository.delete(institutionRepository.getOne(institutionId));
		return "admin/index";
	}

	@GetMapping("/institutionEdit/{institutionId}")
	public String institutionEdit(@PathVariable Long institutionId, Model model) {
		model.addAttribute("institutionEdit", institutionRepository.getOne(institutionId));
		return "admin/institutionEdit";
	}

	@PostMapping("/institutionEdit/{institutionId}")
	public String institutionEdit(@Valid @ModelAttribute("institutionEdit") Institution institutionEdit, BindingResult result, @PathVariable Long institutionId, Model model) {
		if (result.hasErrors()) {
			return "admin/institutionEdit";
		}
		Institution institutionExists = institutionRepository.findByName(institutionEdit.getName());
		if (institutionExists != null) {
			model.addAttribute("institutionExists", "There is already a institution registered with the name provided");
			return "admin/institutionEdit";
		}
		institutionRepository.updateSetNameAndAndDescription(institutionId, institutionEdit.getName(), institutionEdit.getDescription());
		return "admin/index";
	}

	@GetMapping("/adminDelete/{adminID}")
	public String adminDelete(@PathVariable Long adminID, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
		User customUser = currentUser.getUser();
		if (customUser.getId().equals(adminID)) {
			model.addAttribute("adminCurrent", "Nie można usunąc samego siebie");
			return "admin/informationAdmin";
		}
		userRepository.delete(userRepository.getOne(adminID));
		return "admin/index";
	}

	@GetMapping("/adminBlock/{adminID}")
	public String adminBlock(@PathVariable Long adminID, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
		User customUser = currentUser.getUser();
		if (customUser.getId().equals(adminID)) {
			model.addAttribute("adminCurrent", "Nie można zablokowac samego siebie");
			return "admin/informationAdmin";
		}
		userRepository.updateUserSetEnabled(adminID, 0);
		return "admin/index";
	}

	@GetMapping("/adminUnBlock/{adminID}")
	public String adminUnblock(@PathVariable Long adminID) {
		userRepository.updateUserSetEnabled(adminID, 1);
		return "admin/index";
	}

	@GetMapping("/adminEdit/{adminID}")
	public String adminEdit(@PathVariable Long adminID, Model model) {
		model.addAttribute("adminEdit", userRepository.getOne(adminID));
		return "admin/adminEdit";
	}

	@PostMapping("/adminEdit/{adminID}")
	public String adminEdit(@Valid @ModelAttribute("adminEdit") User adminEdit, BindingResult result, @PathVariable Long adminID, Model model) {
		if (result.hasErrors()) {
			return "admin/adminEdit";
		}
		User adminExists = userRepository.findByEmail(adminEdit.getEmail());
		if (adminExists != null) {
			model.addAttribute("adminExists", "There is already a admin registered with the email provided");
			return "admin/adminEdit";
		}
		adminEdit.setPassword(passwordEncoder.encode(adminEdit.getPassword()));
		userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPasswordAAndEnabled(adminID, adminEdit.getFirstName(), adminEdit.getLastName(), adminEdit.getEmail(), adminEdit.getPassword());
		return "admin/index";
	}

	@GetMapping("/adminAdd")
	public String adminAdd(Model model) {
		model.addAttribute("adminAdd", new User());
		return "admin/adminAdd";
	}

	@PostMapping("/adminAdd")
	public String adminAdd(@Valid @ModelAttribute("adminAdd") User adminAdd, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/adminAdd";
		}
		User adminExists = userRepository.findByEmail(adminAdd.getEmail());
		if (adminExists != null) {
			model.addAttribute("adminExists", "There is already an admin registered with the email provided");
			return "admin/adminAdd";
		}
		adminAdd.setPassword(passwordEncoder.encode(adminAdd.getPassword()));
		adminAdd.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_ADMIN"))));
		adminAdd.setEnabled(1);
		userRepository.save(adminAdd);
		return "admin/index";
	}

}
