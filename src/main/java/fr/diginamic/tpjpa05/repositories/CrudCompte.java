package fr.diginamic.tpjpa05.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpjpa05.entities.AssuranceVie;
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.LivretA;
import fr.diginamic.tpjpa05.entities.Operation;

public interface CrudCompte extends CrudRepository<Compte, Long> {

	@Query("select o from Operation o where o.compte.id = :id")
	public Iterable<Operation> findOperationsByCompteId(Long id);
	
	@Query("select a from AssuranceVie a")
	public Iterable<AssuranceVie> getAllAssurancesVie();
	
	@Query("select l from LivretA l")
	public Iterable<LivretA> getAllLivretA();
	
}
