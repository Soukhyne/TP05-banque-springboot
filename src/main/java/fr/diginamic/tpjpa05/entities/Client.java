package fr.diginamic.tpjpa05.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	@NotBlank
	@Column(name="nom")
	private String nom;
	@NotNull 
	@NotBlank
	@Column(name="prenom")
	private String prenom;
	@NotNull
	@Column(name="dateNaissance")
	@DateTimeFormat(iso=ISO.DATE) //a cause de firefox sinon il aurait fallu mettre (pattern="YYYY-dd-MMM")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	@Embedded
	private Adresse adresse;
	
	@ManyToOne
	@JoinColumn(name="id_banque")
	private Banque banque;
	
	public Client() {
		}

	public Client(String nom, String prenom, Date dateNaissance, Adresse adresse, Banque banque) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.banque = banque;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	@Override
	public String toString() {
		return nom + " " + prenom;
	}

}
