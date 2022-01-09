package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.AssuranceVie;
import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;

@RestController
@RequestMapping("/api/assuranceVie")
public class AssuranceVieController extends CompteAbstractController<AssuranceVie> {

    // Create
    @Override
    public AssuranceVie addCompte(@Valid @RequestBody AssuranceVie assuranceVie, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
    	return crudCompte.save(assuranceVie);
    }
    
 // Read ALL
    @Override
    public Iterable<AssuranceVie> getComptes() {
        return crudCompte.getAllAssurancesVie();
    }

    // Read SINGLE
    @Override
    public AssuranceVie getCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("L'assurance Vie avec l'id " + id + " n'existe pas");
        }
        return (AssuranceVie) crudCompte.findById(id).get();
    }
    
    // Read operations
    @Override
    public Iterable<Operation> getOperations(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("L'assurance Vie avec l'id " + id + " n'existe pas");
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
    public AssuranceVie updateCompte(@PathVariable Long id, @Valid @RequestBody AssuranceVie assuranceVie, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
    	if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("L'assurance Vie avec l'id " + id + " n'existe pas");
        }
        if (id != assuranceVie.getId()) {
            throw new CompteNotFoundException("La variable d'URL id = " + id + " est différente de l'id de l'assuranceVie JSON (id = " + assuranceVie.getId() + ")");
        }
        return crudCompte.save(assuranceVie);
    }
    
    // Delete
    @Override
    public ResponseEntity<String> deleteCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("L'assurance Vie avec l'id " + id + " n'existe pas");
        }
        crudCompte.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Assurance Vie supprimé");
    }

	
    
}