package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@ModelAttribute("customUser")
	public User customUser(@AuthenticationPrincipal CurrentUser currentUser) {
		User customUser = currentUser.getUser();
		return customUser;
	}

	@GetMapping("/edit/{userId}")
	public String userEdit(@PathVariable Long userId, Model model) {
		model.addAttribute("userEdit", userRepository.getOne(userId));
		return "user/userEdit";
	}

	@PostMapping("/edit/{userId}")
	public String userEdit(@Valid @ModelAttribute("userEdit") User userEdit, BindingResult result, @PathVariable Long userId, Model model) {
		if (result.hasErrors()) {
			return "user/userEdit";
		}
		User userExists = userRepository.findByEmail(userEdit.getEmail());
		if (userExists == null || userRepository.getOne(userId).getEmail().equals(userEdit.getEmail())) {
			userEdit.setPassword(passwordEncoder.encode(userEdit.getPassword()));
			userRepository.updateUserSetFirstNameAndLastNameAndEmailAndPasswordAAndEnabled(userId, userEdit.getFirstName(), userEdit.getLastName(), userEdit.getEmail(), userEdit.getPassword());
		} else if (userExists != null) {
			model.addAttribute("userExists", "There is already a user registered with the email provided");
			return "user/userEdit";
		}
		return "index";
	}

}


