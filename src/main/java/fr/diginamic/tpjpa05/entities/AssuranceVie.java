package fr.diginamic.tpjpa05.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="assuranceVie")
public class AssuranceVie extends Compte {
	
	@Temporal(TemporalType.DATE)
	@NotNull
	@Future
	@Column(name="dateFin")
	private Date dateFin;
	
	@NotNull
	@Column(name="taux")
	private double taux;

	public AssuranceVie() {
	}

	public AssuranceVie(String numero, double solde, Date dateFin, double taux) {
		super(numero, solde);
		this.dateFin = dateFin;
		this.taux = taux;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	
}
