package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;

@Controller
public class DonationController {


	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("listCategory")
	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	@ModelAttribute("listInstitutions")
	public List<Institution> listInstitutions() {
		return institutionRepository.findAll();
	}

	@GetMapping("/addDonation/{userId}")
	public String addDonation(Model model, @PathVariable Long userId) {
		model.addAttribute("donation", new Donation());;
		model.addAttribute("user", userRepository.getOne(userId));
		return "addDonation";
	}

	@PostMapping("/addDonation/{userId}")
	public String saveDonation(@ModelAttribute Donation donation, @PathVariable Long userId, Model model) {
		donation.setUser(userRepository.getOne(userId));
		donationRepository.save(donation);
		model.addAttribute("user", userRepository.getOne(userId));
		return "DonationConfirmation";
	}

}
