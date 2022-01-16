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

import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.entities.Virement;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudCompte;
import fr.diginamic.tpjpa05.repositories.CrudOperation;

@Controller
@RequestMapping("/virement")
public class VirementWebController extends OperationWebAbstractController<Virement>{

	@Autowired
	private CrudOperation crudOperation;
	
	@Autowired
	private CrudCompte crudCompte;
	
	//affichage liste
	@Override
	public String getOperations(Model model) {
	model.addAttribute("virements", (List<Virement>) crudOperation.getAllVirement());
		model.addAttribute("titre","Liste des virements");
		return "virement/Liste";
	}
	
	//affichage du form
	@Override
	public String addOperation(Model model) {
		model.addAttribute("operationForm", new Virement() );
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Ajout Virement");
		return "virement/add";
	}
	
	//ajout virement
	@Override
	public String add(Model model, @Valid @ModelAttribute("operationForm") Virement operationForm, BindingResult result) throws OperationNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new OperationNotFoundException(s);
		}
		crudOperation.save(operationForm);
		return "redirect:/virement/operations";
	}
	
	//affichage du form update
	@Override
	public String updateOperation(Model model, @PathVariable("id") Long id) {
		model.addAttribute("operationForm", crudOperation.findById(id) );
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Modification Virement");
		return "virement/update";
	}
	
	//update livretA
	@Override
	public String update(Model model, @Valid @ModelAttribute("operationForm") Virement operationForm, BindingResult result) throws OperationNotFoundException{
		if(result.hasErrors()) {
			String s = result.toString();
			throw new OperationNotFoundException(s);
		}
		crudOperation.save(operationForm);
		return "redirect:/virement/operations";
	}
	
	
	@Override
	public String delete(@PathVariable("id") Long id) throws OperationNotFoundException {
		Optional<Operation> operation = crudOperation.findById(id);
		if(operation.isEmpty()) {
			throw new OperationNotFoundException("Virement id :"+id+" non trouvé !");
		}
		crudOperation.deleteById(id);
		return "redirect:/virement/operations";
	}

	
}