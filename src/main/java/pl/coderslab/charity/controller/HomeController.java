package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.serviceCache.InstitutionService;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	@Qualifier("cacheInstitutionService")
	private InstitutionService institutionService;

	@Autowired
	private DonationRepository donationRepository;

	@ModelAttribute("institutionsList")
	public List<Institution> institutionsList() {
		return institutionService.getAllInstitutions();
	}

	@ModelAttribute("sumQuantityDonation")
	public int sumQuantityDonation() {
		return donationRepository.sumQuantity();
	}

	@ModelAttribute("countInstitution")
	public int countInstitution() {
		return donationRepository.countInstitution();
	}

	@RequestMapping("/")
	public String homeAction(Model model, @AuthenticationPrincipal CurrentUser customUser) {
		if (customUser != null) {
			User entityUser = customUser.getUser();
			model.addAttribute("customUser", entityUser);
		}
		return "index";
	}

}


