package fr.diginamic.tpjpa05.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.Banque;
import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.exceptions.BanqueNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudBanque;

@RestController
@RequestMapping("/api/banque")
public class BanqueController {


	@Autowired
	private CrudBanque crudBanque;

	//Create
	@PostMapping
	public Banque addBanque(@Valid @RequestBody Banque Banque, BindingResult result) throws BanqueNotFoundException {
		if(result.hasErrors()) {
			String s = result.toString();
			throw new BanqueNotFoundException(s);
		}
		return crudBanque.save(Banque);
	}

	//read all banks
	@GetMapping("all")
	public Iterable<Banque> getBanque(){
		return crudBanque.findAll();
	}
	
	//read one bank
	@GetMapping("{id}")
	public Optional<Banque> getBanque(@PathVariable("id") Long id) throws BanqueNotFoundException {
		if(crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le Banque avec l'id : " + id+" n'existe pas"); 
		}
		return crudBanque.findById(id);
	}
	
	//read one clients
	@GetMapping("{id}/client")
	public Iterable<Client> getClients(@PathVariable("id") Long id) throws BanqueNotFoundException {
		if(crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le Banque avec l'id : " + id+" n'existe pas"); 
		}
		return crudBanque.findClientsByIdBanque(id);
	}
	
	//update bank
	@PutMapping("{id}")
	public Banque updateBanque(@PathVariable("id") Long id, @Valid @RequestBody Banque banque, BindingResult result) throws BanqueNotFoundException {
		if(result.hasErrors()) {
			String s = result.toString();
			throw new BanqueNotFoundException(s);
		}
		if(id != banque.getId()) {
			throw new BanqueNotFoundException("La variable d'URL id " + id+" est differente de l'id de la Banque JSON"); 
		}
		if(crudBanque.findById(id).isEmpty()) { 
			throw new BanqueNotFoundException("Le Banque avec l'id : " + id+" n'existe pas"); 
		}
		return crudBanque.save(banque);
	}
	
	//delete bank
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBanque(@PathVariable("id") Long id) throws BanqueNotFoundException {
		if(crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le Banque avec l'id : " + id+" n'existe pas"); 
		}
		
		crudBanque.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Banque supprimé!");
	}

}
