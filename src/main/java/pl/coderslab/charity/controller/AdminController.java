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

	@GetMapping("/admin/info")
	public String adminInfo() {
		return "admin/informationAdmin";
	}

	@GetMapping("/admin/delete/{adminID}")
	public String adminDelete(@PathVariable Long adminID, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
		User customUser = currentUser.getUser();
		if (customUser.getId().equals(adminID)) {
			model.addAttribute("adminCurrent", "Nie można usunąć samego siebie");
			return "admin/informationAdmin";
		}
		return "admin/confirmationOnDelete";
	}

	@PostMapping("/admin/delete/{adminID}")
	public String adminDelete(@PathVariable Long adminID) {
		userRepository.delete(userRepository.getOne(adminID));
		return "admin/index";
	}

	@GetMapping("/admin/block/{adminID}")
	public String adminBlock(@PathVariable Long adminID, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
		User customUser = currentUser.getUser();
		if (customUser.getId().equals(adminID)) {
			model.addAttribute("adminCurrent", "Nie można zablokowac samego siebie");
			return "admin/informationAdmin";
		}
		userRepository.updateUserSetEnabled(adminID, 0);
		return "admin/index";
	}

	@GetMapping("/admin/unBlock/{adminID}")
	public String adminUnblock(@PathVariable Long adminID) {
		userRepository.updateUserSetEnabled(adminID, 1);
		return "admin/index";
	}

	@GetMapping("/admin/edit/{adminID}")
	public String adminEdit(@PathVariable Long adminID, Model model) {
		model.addAttribute("adminEdit", userRepository.getOne(adminID));
		return "admin/adminEdit";
	}

	@PostMapping("/admin/edit/{adminID}")
	public String adminEdit(@Valid @ModelAttribute("adminEdit") User adminEdit, BindingResult result, @PathVariable Long adminID, Model model) {
		if (result.hasErrors()) {
			return "admin/adminEdit";
		}
		User adminExists = userRepository.findByEmail(adminEdit.getEmail());
		if (userRepository.getOne(adminID).getEmail().equals(adminEdit.getEmail()) || adminExists == null) {
			adminEdit.setPassword(passwordEncoder.encode(adminEdit.getPassword()));
			userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPasswordAAndEnabled(adminID, adminEdit.getFirstName(), adminEdit.getLastName(), adminEdit.getEmail(), adminEdit.getPassword());
		} else if (adminExists != null) {
			model.addAttribute("adminExists", "There is already a admin registered with the email provided");
			return "admin/adminEdit";
		}
		return "admin/index";
	}

	@GetMapping("/admin/add")
	public String adminAdd(Model model) {
		model.addAttribute("adminAdd", new User());
		return "admin/adminAdd";
	}

	@PostMapping("/admin/add")
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
