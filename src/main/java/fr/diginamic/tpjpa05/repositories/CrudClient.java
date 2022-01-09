package fr.diginamic.tpjpa05.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.entities.Compte;

public interface CrudClient extends CrudRepository<Client, Long> {

	@Query("select c from Compte c where :client MEMBER OF c.clients")
	public Iterable<Compte> findCompteByClient(Client client);
}
