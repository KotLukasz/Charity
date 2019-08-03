package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("customUser")
	public User customUser (@AuthenticationPrincipal CurrentUser currentUser) {
		User customUser = currentUser.getUser();
	return customUser;
	}

	@ModelAttribute("users")
	public List<User> users () {
		return userRepository.findAll();
	}

//	@ModelAttribute("admins")
//	public List<User> admins () {
//		return userRepository.findAllByRolesEquals("ROLE_ADMIN");
//	}
//
//	@ModelAttribute("institutionsList")
//	public List<Institution> institutionsList() {
//		return institutionRepository.findAll();
//	}

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



}
