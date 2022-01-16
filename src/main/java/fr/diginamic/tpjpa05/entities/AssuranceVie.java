package fr.diginamic.tpjpa05.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="assuranceVie")
public class AssuranceVie extends Compte {
	
	@DateTimeFormat(iso=ISO.DATE) //a cause de firefox sinon il aurait fallu mettre (pattern="YYYY-dd-MMM")
	@Temporal(TemporalType.DATE)
	@NotNull
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
