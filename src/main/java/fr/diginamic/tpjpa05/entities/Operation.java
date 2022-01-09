package fr.diginamic.tpjpa05.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="operation")
public class Operation {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	
	@NotNull
	@Column(name="montant")
	private double montant;
	
	@NotNull
	@NotBlank
	@Column(name="motif")
	private String motif;
	
	@ManyToOne
	@JoinColumn(name="id_compte")
	private Compte compte ;

	public Operation() {
	}

	public Operation(Compte compte, Date date, double montant, String motif) {
		this.compte = compte;
		this.date = date;
		this.montant = montant;
		this.motif = motif;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", date=" + date + ", montant=" + montant + ", motif=" + motif + "]";
	}

	
}
