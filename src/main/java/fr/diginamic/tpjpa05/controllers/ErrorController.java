package fr.diginamic.tpjpa05.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.diginamic.tpjpa05.exceptions.BanqueNotFoundException;
import fr.diginamic.tpjpa05.exceptions.ClientNotFoundException;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;

@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(value = {ClientNotFoundException.class})
	public ResponseEntity<String> onErrorClient(ClientNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClientError : " + ex.getMessage());
	}
	
	@ExceptionHandler(value = {BanqueNotFoundException.class})
	public ResponseEntity<String> onErrorEmprunt(BanqueNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BanqueError : " + ex.getMessage());
	}
	
	@ExceptionHandler(value = {CompteNotFoundException.class})
	public ResponseEntity<String> onErrorLivre(CompteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CompteError : " + ex.getMessage());
	}
	
	@ExceptionHandler(value = {OperationNotFoundException.class})
	public ResponseEntity<String> onErrorLivre(OperationNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OperationError : " + ex.getMessage());
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<String> onError(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Other error : " + ex.getMessage());
	}

}
