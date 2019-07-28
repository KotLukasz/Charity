package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/user")
public class DonationController {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DonationRepository donationRepository;

	@ModelAttribute("listCategory")
	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	@ModelAttribute("listInstitutions")
	public List<Institution> listInstitutions() {
		return institutionRepository.findAll();
	}

	@GetMapping("/addDonation")
	public String saveDonation(Model model, @AuthenticationPrincipal CurrentUser customUser) {
		User entityUser = customUser.getUser();
		model.addAttribute("customUser", entityUser);
		model.addAttribute("donation", new Donation());
		return "donation";
	}

	@PostMapping("/addDonation")
	public String saveDonation(@ModelAttribute Donation donation, @AuthenticationPrincipal CurrentUser customUser, Model model) {
		User entityUser = customUser.getUser();
		model.addAttribute("customUser", entityUser);
		donation.setUser(entityUser);
		donationRepository.save(donation);
		return "donationConfirmation";
	}

}
