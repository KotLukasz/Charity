package pl.coderslab.charity.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.pojo.ViewMode;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.AuthenticationService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("ViewMode", new ViewMode());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute ViewMode viewMode) {
		if (!authenticationService.givenEmailExistInUserDatabase(viewMode.getEmail())) {
			String hashedPassword = BCrypt.hashpw(viewMode.getPassword(), BCrypt.gensalt());
			viewMode.setPassword(hashedPassword);
			User user = new User();
			user.setPassword(viewMode.getPassword());
			user.setEmail(viewMode.getEmail());
			user.setFirstName(viewMode.getFirstName());
			user.setLastName(viewMode.getLastName());
			userRepository.save(user);
			return "redirect:/addDonation/" + user.getId();
		}
		return "register";
	}

	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("viewMode", new ViewMode());
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute ViewMode viewMode) {
		if (authenticationService.givenEmailExistInUserDatabase(viewMode.getEmail())) {
			User user = authenticationService.authenticateUser(viewMode.getEmail(), viewMode.getPassword());
			if (user != null) {
				return "redirect:/addDonation/" + user.getId();
			}
		}
		return "login";
	}

}

