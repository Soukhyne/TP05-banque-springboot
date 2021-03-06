package fr.diginamic.tpjpa05.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;
import fr.diginamic.tpjpa05.repositories.CrudCompte;

@Controller
@RequestMapping("/compte")
public class CompteWebController extends CompteWebAbstractController <Compte>{

	@Autowired
	private CrudCompte crudCompte;
	
	@Autowired
	private CrudClient crudClient;
	
	public CompteWebController() {
		// TODO Auto-generated constructor stub
	}
	
	//affichage liste
	@Override
	public String getComptes(Model model) {
		model.addAttribute("comptes", (List<Compte>) crudCompte.findAll());
		model.addAttribute("titre","Liste des comptes");
		return "comptes/Liste";
	}
	
	//affichage du form
	@Override
	public String addCompte(Model model) {
		model.addAttribute("compteForm", new Compte() );
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Ajout compte");
		return "comptes/add";
	}
	
	//ajout compte
	@Override
	public String add(Model model, @Valid @ModelAttribute("compteForm") Compte compteForm, BindingResult result) throws CompteNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new CompteNotFoundException(s);
		}
		crudCompte.save(compteForm);
		return "redirect:/compte/comptes";
	}
	
	//affichage du form update
	@Override
	public String updateCompte(Model model, @PathVariable("id") Long id) {
		model.addAttribute("compteForm", crudCompte.findById(id) );
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Modification compte");
		return "comptes/update";
	}
	
	//update compte
	@Override
	public String update(Model model, @Valid @ModelAttribute("compteForm") Compte compteForm, BindingResult result) throws CompteNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new CompteNotFoundException(s);
		}
		crudCompte.save(compteForm);
		return "redirect:/compte/comptes";
	}
	
	
	@Override
	public String delete(@PathVariable("id") Long id) throws CompteNotFoundException {
		Optional<Compte> compte = crudCompte.findById(id);
		if(compte.isEmpty()) {
			throw new CompteNotFoundException("Compte id :"+id+" non trouv?? !");
		}
		crudCompte.deleteById(id);
		return "redirect:/compte/comptes";
	}
}
