package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudOperation;

@RestController
public abstract class OperationAbstractController<T extends Operation> {

    @Autowired
    protected CrudOperation crudOperation;

    // Create
    @PostMapping
    public abstract T addOperation(@Valid @RequestBody T operation, BindingResult result) throws OperationNotFoundException;

    // Read ALL
    @GetMapping("all")
    public abstract Iterable<T> getOperations();

    // Read SINGLE
    @GetMapping("{id}")
    public abstract T getOperation(@PathVariable Long id) throws OperationNotFoundException;

    // Read COMPTE
    @GetMapping("/{id}/compte")
    public abstract Compte getCompte(@PathVariable Long id) throws OperationNotFoundException;

    // Update
    @PutMapping("/{id}")
    public abstract T updateOperation(@PathVariable Long id, @Valid @RequestBody T operation, BindingResult result) throws OperationNotFoundException;

    // Delete
    @DeleteMapping("/{id}")
    public abstract ResponseEntity<String> deleteOperation(@PathVariable Long id) throws OperationNotFoundException;

}