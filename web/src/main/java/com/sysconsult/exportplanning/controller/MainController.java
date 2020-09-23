package com.sysconsult.exportplanning.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sysconsult.exportplanning.model.Role;
import com.sysconsult.exportplanning.model.User;
import com.sysconsult.exportplanning.repository.RoleRepository;
import com.sysconsult.exportplanning.repository.UserRepository;
import com.sysconsult.exportplanning.service.UserServiceImpl;

@Controller
public class MainController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute @Valid User user) {
		ModelAndView mav = new ModelAndView("register");
		if (userRepo.existsByEmail(user.getEmail())) {
			mav.addObject("message", "Email est déjà existant");
			return mav;
		}
		ArrayList<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setDescription("description");
		role.setLabel("ADMIN");
		roles.add(roleRepo.save(role));

		user.setRoles(roles);
		userService.save(user);

		mav.addObject("message", "Un utilisateur est crée");
		return mav;
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
