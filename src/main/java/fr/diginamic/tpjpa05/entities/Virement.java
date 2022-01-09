package fr.diginamic.tpjpa05.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Virement extends Operation{
		
	@NotNull
	@NotBlank
	@Column(name="beneficiaire")
	private String beneficiaire;

	public Virement() {
	}

	public Virement(Compte compte, Date date, double montant, String motif, String beneficiaire) {
		super(compte, date, montant, motif);
		this.beneficiaire = beneficiaire;
	}

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	@Override
	public String toString() {
		return "Virement [beneficiaire=" + beneficiaire + "]";
	}
	
}
