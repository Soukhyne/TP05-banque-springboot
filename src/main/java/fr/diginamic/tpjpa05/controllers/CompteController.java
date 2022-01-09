package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;

@RestController
@RequestMapping("/api/compte")
public class CompteController extends CompteAbstractController<Compte> {

    // Create
    @Override
    public Compte addCompte(@Valid @RequestBody Compte compte, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
        return crudCompte.save(compte);
    }
    
    // Read ALL
    @Override
    public Iterable<Compte> getComptes() {
        return crudCompte.findAll();
    }
    
    // Read SINGLE
    @Override
    public Compte getCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le compte avec l'id " + id + " n'existe pas");
        }
        return crudCompte.findById(id).get();
    }
    
    // Read operations
    @Override
    public Iterable<Operation> getOperations(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le compte avec l'id " + id + " n'existe pas");
        }
        return crudCompte.findOperationsByCompteId(id);
    }
    
    // Read clients
	@Override
	public Iterable<Client> getClients(Long id) throws CompteNotFoundException {
		if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le compte avec l'id : " + id+" n'existe pas");
        }
        return crudCompte.findById(id).get().getClients();
	}
    
    // Update
    @Override
    public Compte updateCompte(@PathVariable Long id, @Valid @RequestBody Compte compte, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
    	if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le compte avec l'id " + id + " n'existe pas");
        }
        if (id != compte.getId()) {
            throw new CompteNotFoundException("La variable d'URL id = " + id + " est différente de l'id du compte JSON (id = " + compte.getId() + ")");
        }
        return crudCompte.save(compte);
    }
    
    // Delete
    @Override
    public ResponseEntity<String> deleteCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le compte avec l'id " + id + " n'existe pas");
        }
        crudCompte.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
    }


    
}