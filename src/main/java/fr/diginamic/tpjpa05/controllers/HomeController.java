package fr.diginamic.tpjpa05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		String titre = "Welcome Madeline";
		model.addAttribute("titre", titre);
		return "home";
	}

}
