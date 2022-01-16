package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudOperation;

@Controller
public abstract class OperationWebAbstractController<T extends Operation> {

    @Autowired
    protected CrudOperation crudOperation;

    //affichage liste
  	@GetMapping("/operations")
  	public abstract String getOperations(Model model);
  	
  	//affichage du form
  	@GetMapping("/add")
  	public abstract String addOperation(Model model);
  	
  	//ajout compte
  	@PostMapping("/add")
  	public abstract String add(Model model, @Valid @ModelAttribute("operationForm") T operationForm, BindingResult result) throws OperationNotFoundException;
  	
  	//affichage du form update
  	@GetMapping("/update/{id}")
  	public abstract String updateOperation(Model model, @PathVariable("id") Long id);
  	
  	//update compte
  	@PostMapping("/update")
  	public abstract String update(Model model, @Valid @ModelAttribute("operationForm") T operationForm, BindingResult result) throws OperationNotFoundException;
  	
  	@GetMapping("/delete/{id}")
  	public abstract String delete(@PathVariable("id") Long id) throws OperationNotFoundException;

}