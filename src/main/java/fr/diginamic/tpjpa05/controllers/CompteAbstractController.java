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

import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudCompte;

@RestController
public abstract class CompteAbstractController<T extends Compte> {

    @Autowired
    protected CrudCompte crudCompte;

    // Create
    @PostMapping
    public abstract T addCompte(@Valid @RequestBody T compte, BindingResult result) throws CompteNotFoundException;

    // Read ALL
    @GetMapping("all")
    public abstract Iterable<T> getComptes();

    // Read SINGLE
    @GetMapping("{id}")
    public abstract T getCompte(@PathVariable Long id) throws CompteNotFoundException;
    
    //read CLIENT
    @GetMapping("{id}/client")
    public abstract Iterable<Client> getClients(@PathVariable Long id) throws CompteNotFoundException; 

    // Read operations
    @GetMapping("/{id}/operations")
    public abstract Iterable<Operation> getOperations(@PathVariable Long id) throws CompteNotFoundException;

    // Update
    @PutMapping("/{id}")
    public abstract T updateCompte(@PathVariable Long id, @Valid @RequestBody T compte, BindingResult result) throws CompteNotFoundException;

    // Delete
    @DeleteMapping("/{id}")
    public abstract ResponseEntity<String> deleteCompte(@PathVariable Long id) throws CompteNotFoundException;

}