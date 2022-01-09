package fr.diginamic.tpjpa05.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="compte")
@Inheritance(strategy = InheritanceType.JOINED)

public class Compte {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotBlank
	@Column(name="numero")
	private String numero;
	
	@NotNull
	@Column(name="solde")
	private double solde;

	@ManyToMany
	@JoinTable(name="client_compte",
		joinColumns=@JoinColumn(name="id_compte", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="id_client", referencedColumnName="id")
	)
	private Set<Client> clients;

	public Compte() {
		this.clients = new HashSet<>();
	}

	public Compte(String numero, double solde) {
		this.numero = numero;
		this.solde = solde;
		this.clients = new HashSet<>();
	}
	
	public Compte(String numero, double solde, Set<Client> clients) {
		this.numero = numero;
		this.solde = solde;
		this.clients = clients;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", numero=" + numero + ", solde=" + solde + "]";
	}

	
}
