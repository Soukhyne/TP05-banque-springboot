package fr.diginamic.tpjpa05.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.entities.Virement;

public interface CrudOperation extends CrudRepository<Operation, Long> {
	
	@Query("select v from Virement v")
	public Iterable<Virement> getAllVirement();

}
