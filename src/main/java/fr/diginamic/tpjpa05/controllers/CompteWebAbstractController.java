package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;

@Controller
public abstract class CompteWebAbstractController<T extends Compte> {

	//affichage liste
	@GetMapping("/comptes")
	public abstract String getComptes(Model model);
	
	//affichage du form
	@GetMapping("/add")
	public abstract String addCompte(Model model);
	
	//ajout compte
	@PostMapping("/add")
	public abstract String add(Model model, @Valid @ModelAttribute("compteForm") T compteForm, BindingResult result) throws CompteNotFoundException;
	
	//affichage du form update
	@GetMapping("/update/{id}")
	public abstract String updateCompte(Model model, @PathVariable("id") Long id);
	
	//update compte
	@PostMapping("/update")
	public abstract String update(Model model, @Valid @ModelAttribute("compteForm") T compteForm, BindingResult result) throws CompteNotFoundException;
	
	@GetMapping("/delete/{id}")
	public abstract String delete(@PathVariable("id") Long id) throws CompteNotFoundException;
}
