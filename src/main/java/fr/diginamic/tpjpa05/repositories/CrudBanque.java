package fr.diginamic.tpjpa05.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpjpa05.entities.Banque;
import fr.diginamic.tpjpa05.entities.Client;

public interface CrudBanque extends CrudRepository<Banque, Long> {
	
	@Query("select c from Client c where c.banque.id = :id")
	public Iterable<Client> findClientsByIdBanque(Long id);

}
