package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CurrentUser;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/admin")
	@ResponseBody
	public String admin(@AuthenticationPrincipal CurrentUser customUser) {
		User entityUser = customUser.getUser();
		return "Hello " + entityUser.getId();
	}

}
