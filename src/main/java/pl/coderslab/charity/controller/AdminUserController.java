package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.serviceCache.InstitutionService;


import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	@Autowired
	@Qualifier("cacheInstitutionService")
	private InstitutionService institutionService;

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
		return institutionService.getAllInstitutions();
	}

	@GetMapping
	public String index() {
		return "admin/index";
	}

	@GetMapping("/info")
	public String userInfo() {
		return "admin/informationUser";
	}

	@GetMapping("/delete/{userId}")
	public String userDelete() {
		return "admin/confirmationOnDelete";
	}

	@PostMapping("/delete/{userId}")
	public String userDelete(@PathVariable Long userId) {
		userRepository.delete(userRepository.getOne(userId));
		return "admin/index";
	}


	@GetMapping("/block/{userId}")
	public String userBlock(@PathVariable Long userId) {
		userRepository.updateUserSetEnabled(userId, 0);
		return "admin/index";
	}

	@GetMapping("/unBlock/{userId}")
	public String userUnBlock(@PathVariable Long userId) {
		userRepository.updateUserSetEnabled(userId, 1);
		return "admin/index";
	}

	@GetMapping("/edit/{userId}")
	public String userEdit(@PathVariable Long userId, Model model) {
		model.addAttribute("userEdit", userRepository.getOne(userId));
		return "admin/userEdit";
	}

	@PostMapping("/edit/{userId}")
	public String userEdit(@Valid @ModelAttribute("userEdit") User userEdit, BindingResult result, @PathVariable Long userId, Model model) {
		if (result.hasErrors()) {
			return "admin/userEdit";
		}
		User userExists = userRepository.findByEmail(userEdit.getEmail());
		if (userExists == null || userRepository.getOne(userId).getEmail().equals(userEdit.getEmail())) {
			userEdit.setPassword(passwordEncoder.encode(userEdit.getPassword()));
			userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPassword(userId, userEdit.getFirstName(), userEdit.getLastName(), userEdit.getEmail(), userEdit.getPassword());
		} else if (userExists != null) {
			model.addAttribute("userExists", "There is already a user registered with the email provided");
			return "admin/userEdit";
		}
		return "admin/index";
	}

}
