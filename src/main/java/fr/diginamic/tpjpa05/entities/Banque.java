package fr.diginamic.tpjpa05.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="banque")
public class Banque {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotBlank
	@Column(name="nom")
	private String nom;
	

	public Banque() {
	}

	public Banque(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

}
