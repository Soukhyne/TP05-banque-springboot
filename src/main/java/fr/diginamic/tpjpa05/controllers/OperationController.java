package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;

@RestController
@RequestMapping("/api/operation")
public class OperationController extends OperationAbstractController<Operation> {

    // Create
    @Override
    public Operation addOperation(@Valid @RequestBody Operation operation, BindingResult result) throws OperationNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new OperationNotFoundException(s);
		}
    	return crudOperation.save(operation);
    }
    
    // Read ALL
    @Override
    public Iterable<Operation> getOperations() {
        return crudOperation.findAll();
    }
    
    // Read SINGLE
    @Override
    public Operation getOperation(@PathVariable Long id) throws OperationNotFoundException {
        if (crudOperation.findById(id).isEmpty()) {
            throw new OperationNotFoundException("L'operation avec l'id " + id + " n'existe pas");
        }
        return crudOperation.findById(id).get();
    }
    
    // Read COMPTE
   	@Override
	public Compte getCompte(@PathVariable Long id) throws OperationNotFoundException {
   		if (crudOperation.findById(id).isEmpty()) {
            throw new OperationNotFoundException("L'opération avec l'id " + id + " n'existe pas");
        }
   		Compte compte = crudOperation.findById(id).get().getCompte();
        return compte;
	}
    
    // Update
    @Override
    public Operation updateOperation(@PathVariable Long id, @Valid @RequestBody Operation operation, BindingResult result) throws OperationNotFoundException {
    	if(result.hasErrors()) {
    		String s = result.toString();
			throw new OperationNotFoundException(s);
		}
    	if (crudOperation.findById(id).isEmpty()) {
            throw new OperationNotFoundException("L'operation avec l'id " + id + " n'existe pas");
        }
        if (id != operation.getId()) {
            throw new OperationNotFoundException("La variable d'URL id = " + id + " est différente de l'id de l'operation JSON (id = " + operation.getId() + ")");
        }
        return crudOperation.save(operation);
    }
    
    // Delete
    @Override
    public ResponseEntity<String> deleteOperation(@PathVariable Long id) throws OperationNotFoundException {
        if (crudOperation.findById(id).isEmpty()) {
            throw new OperationNotFoundException("L'operation avec l'id " + id + " n'existe pas");
        }
        crudOperation.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Operation supprimée");
    }


    
}