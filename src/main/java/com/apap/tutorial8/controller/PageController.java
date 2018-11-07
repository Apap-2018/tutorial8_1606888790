package com.apap.tutorial8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("updateSuccess", false);
		model.addAttribute("notReconfirmed", false);
		model.addAttribute("notSameAsOld", false);
		return "home";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
