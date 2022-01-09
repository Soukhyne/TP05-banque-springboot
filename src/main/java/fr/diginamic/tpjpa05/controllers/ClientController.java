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
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.exceptions.ClientNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private CrudClient crudClient;

	//Create
	@PostMapping
	public Client addClient(@Valid @RequestBody Client client, BindingResult result) throws ClientNotFoundException {
		if(result.hasErrors()) {
			String s = result.toString();
			throw new ClientNotFoundException(s);
		}
		return crudClient.save(client);
	}
	
	

	//read all clients
	@GetMapping("all")
	public Iterable<Client> getClient(){
		return crudClient.findAll();
	}
	
	//read one client
	@GetMapping("{id}")
	public Optional<Client> getClient(@PathVariable("id") Long id) throws ClientNotFoundException {
		if(crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id : " + id+" n'existe pas"); 
		}
		return crudClient.findById(id);
	}
	
	//read bank
	@GetMapping("{id}/banque")
	public Banque getBanque(@PathVariable Long id) throws ClientNotFoundException {
   		if (crudClient.findById(id).isEmpty()) {
            throw new ClientNotFoundException("Le client avec l'id : " + id+" n'existe pas");
        }
   		Banque banque = crudClient.findById(id).get().getBanque();
        return banque;
	}
	
	//read comptes
	@GetMapping("{id}/compte")
	public Iterable<Compte> getComptes(@PathVariable Long id) throws ClientNotFoundException {
   		if (crudClient.findById(id).isEmpty()) {
            throw new ClientNotFoundException("Le client avec l'id : " + id+" n'existe pas");
        }
        return crudClient.findCompteByClient(crudClient.findById(id).get());
	}
	
	//update client
	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Long id, @Valid @RequestBody Client client, BindingResult result) throws ClientNotFoundException {
		if(result.hasErrors()) {
			String s = result.toString();
			throw new ClientNotFoundException(s);
		}
		if(id != client.getId()) {
			throw new ClientNotFoundException("La variable d'URL id " + id+" est differente de l'id du client JSON"); 
		}
		if(crudClient.findById(id).isEmpty()) { 
			throw new ClientNotFoundException("Le client avec l'id : " + id+" n'existe pas"); 
		}
		return crudClient.save(client);
	}
	
	//delete client
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) throws ClientNotFoundException {
		if(crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id : " + id+" n'existe pas"); 
		}
		
		crudClient.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Client supprimé!");
	}

}
