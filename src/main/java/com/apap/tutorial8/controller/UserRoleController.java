package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(Model model, String username, String oldPassword, String newPassword, String newPasswordConfirm) {
		UserRoleModel user = userService.findUserByUsername(username);
		
//		cek sama tak dengan pass lama
		if (!userService.checkCurrentPassword(user,oldPassword)) {
			model.addAttribute("notSameAsOld", true);
			return "home";
		}
//		cek condisi pw
		if (!userService.checkCondition(newPassword)) {
			model.addAttribute("notLikeCondition", true);
			return "home";
		}
//		cek reconfirm
		if (!userService.checkMatchPas(newPassword,newPasswordConfirm)) {
			model.addAttribute("notReconfirmed", true);
			return "home";
		}
		user.setPassword(newPassword);
		userService.addUser(user);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("notReconfirmed", false);
		model.addAttribute("notSameAsOld", false);
		return "home";
	}
}
