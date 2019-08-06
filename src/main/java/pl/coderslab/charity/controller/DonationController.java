package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/donation")
public class DonationController {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("customUser")
	public User customUser(@AuthenticationPrincipal CurrentUser currentUser) {
		User customUser = currentUser.getUser();
		return customUser;
	}

	@ModelAttribute("listCategory")
	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	@ModelAttribute("listInstitutions")
	public List<Institution> listInstitutions() {
		return institutionRepository.findAll();
	}

	@GetMapping("/addDonation/{userId}")
	public String saveDonation(Model model) {
		model.addAttribute("donation", new Donation());
		return "user/donationAdd";
	}

	@PostMapping("/addDonation/{userId}")
	public String saveDonation(@Valid @ModelAttribute Donation donation, BindingResult result, Model model, @PathVariable Long userId) {
		if (result.hasErrors()) {
			return "user/donationAdd";
		}
		donation.setUser(userRepository.getOne(userId));
		donationRepository.save(donation);
		return "user/donationConfirmation";
	}

	@GetMapping("/donations/{userId}")
	public String showDonations(@PathVariable Long userId, Model model) {
		model.addAttribute("donations", donationRepository.findAllByUser(userRepository.getOne(userId)));
		return "user/donationList";
	}

}
