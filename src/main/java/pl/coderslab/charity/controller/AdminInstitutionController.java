package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.serviceCache.InstitutionService;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/institution")
public class AdminInstitutionController {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	@Qualifier("cacheInstitutionService")
	private InstitutionService institutionService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	@Qualifier("institutions")
	private CaffeineCache institutionsCache;

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
	public String institutionInfo() {
		return "admin/informationInstitution";
	}


	@GetMapping("/add")
	public String institutionAdd(Model model) {
		model.addAttribute("institution", new Institution());
		return "admin/institutionAdd";
	}

	@PostMapping("/add")
	public String institutionAdd(@Valid @ModelAttribute("institution") Institution institution, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/institutionAdd";
		}
		Institution institutionExists = institutionRepository.findByName(institution.getName());
		if (institutionExists != null) {
			model.addAttribute("institutionExists", "There is already a institution registered with the name provided");
			return "admin/institutionAdd";
		}
		institutionRepository.save(institution);
		institutionsCache.clear();
		return "admin/index";
	}

	@GetMapping("/delete/{institutionId}")
	public String institutionDelete() {
		return "admin/confirmationOnDelete";
	}

	@PostMapping("/delete/{institutionId}")
	public String institutionDelete(@PathVariable Long institutionId) {
		institutionRepository.delete(institutionRepository.getOne(institutionId));
		institutionsCache.clear();
		return "admin/index";
	}

	@GetMapping("/edit/{institutionId}")
	public String institutionEdit(@PathVariable Long institutionId, Model model) {
		model.addAttribute("institutionEdit", institutionRepository.getOne(institutionId));
		return "admin/institutionEdit";
	}

	@PostMapping("/edit/{institutionId}")
	public String institutionEdit(@Valid @ModelAttribute("institutionEdit") Institution institutionEdit, BindingResult result, @PathVariable Long institutionId, Model model) {
		if (result.hasErrors()) {
			return "admin/institutionEdit";
		}
		Institution institutionExists = institutionRepository.findByName(institutionEdit.getName());
		if (institutionExists == null ||institutionRepository.getOne(institutionId).getName().equals(institutionEdit.getName())) {
			institutionRepository.updateSetNameAndAndDescription(institutionId, institutionEdit.getName(), institutionEdit.getDescription());
		} else if (institutionExists != null) {
			model.addAttribute("institutionExists", "There is already a institution registered with the name provided");
			return "admin/institutionEdit";
		}
		institutionsCache.clear();
		return "admin/index";
	}

}
