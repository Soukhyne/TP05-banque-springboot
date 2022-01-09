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
import fr.diginamic.tpjpa05.entities.LivretA;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;

@RestController
@RequestMapping("/api/livretA")
public class LivretAController extends CompteAbstractController<LivretA> {

    // Create
    @Override
    public LivretA addCompte(@Valid @RequestBody LivretA livretA, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
    	return crudCompte.save(livretA);
    }
    
 // Read ALL
    @Override
    public Iterable<LivretA> getComptes() {
        return crudCompte.getAllLivretA();
    }

    // Read SINGLE
    @Override
    public LivretA getCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le livretA avec l'id " + id + " n'existe pas");
        }
        return (LivretA) crudCompte.findById(id).get();
    }
    
    // Read operations
    @Override
    public Iterable<Operation> getOperations(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le livretA avec l'id " + id + " n'existe pas");
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
    public LivretA updateCompte(@PathVariable Long id, @Valid @RequestBody LivretA livretA, BindingResult result) throws CompteNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new CompteNotFoundException(s);
		}
    	if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le livretA avec l'id " + id + " n'existe pas");
        }
        if (id != livretA.getId()) {
            throw new CompteNotFoundException("La variable d'URL id = " + id + " est différente de l'id du livretA JSON (id = " + livretA.getId() + ")");
        }
        return crudCompte.save(livretA);
    }
    
    // Delete
    @Override
    public ResponseEntity<String> deleteCompte(@PathVariable Long id) throws CompteNotFoundException {
        if (crudCompte.findById(id).isEmpty()) {
            throw new CompteNotFoundException("Le livretA avec l'id " + id + " n'existe pas");
        }
        crudCompte.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("LivretA supprimé");
    }

	
    
}