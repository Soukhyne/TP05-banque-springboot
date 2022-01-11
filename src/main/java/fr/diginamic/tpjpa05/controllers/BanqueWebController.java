package fr.diginamic.tpjpa05.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.tpjpa05.entities.Banque;
import fr.diginamic.tpjpa05.exceptions.BanqueNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudBanque;

@Controller
@RequestMapping("/banque")
public class BanqueWebController {

	@Autowired
	private CrudBanque crudBanque;
	
	public BanqueWebController() {
		// TODO Auto-generated constructor stub
	}
	
	//affichage liste
	@GetMapping("/banques")
	public String getBanques(Model model) {
		model.addAttribute("banques", (List<Banque>) crudBanque.findAll());
		model.addAttribute("crudBanque", crudBanque );
		model.addAttribute("titre","Liste des banques");
		return "banques/Liste";
	}
	
	//affichage du form
	@GetMapping("/add")
	public String addBanque(Model model) {
		model.addAttribute("banqueForm", new Banque() );
		model.addAttribute("titre","Ajout banque");
		return "banques/add";
	}

	//ajout banque
	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("banqueForm") Banque banqueForm, BindingResult result) throws BanqueNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new BanqueNotFoundException(s);
		}
		crudBanque.save(banqueForm);
		return "redirect:/banque/banques";
	}
	
	//affichage du form update
	@GetMapping("/update/{id}")
	public String updateBanque(Model model, @PathVariable("id") Long id) {
		model.addAttribute("banqueForm", crudBanque.findById(id) );
		model.addAttribute("titre","Modification banque");
		return "banques/update";
	}
	
	//update client
	@PostMapping("/update")
	public String update(Model model, @Valid @ModelAttribute("banqueForm") Banque banqueForm, BindingResult result) throws BanqueNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new BanqueNotFoundException(s);
		}
		crudBanque.save(banqueForm);
		return "redirect:/banque/banques";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) throws BanqueNotFoundException {
		Optional<Banque> banque = crudBanque.findById(id);
		if(banque.isEmpty()) {
			throw new BanqueNotFoundException("Banque id :"+id+" non trouvé !");
		}
		crudBanque.deleteById(id);
		return "redirect:/banque/banques";
	}
}
