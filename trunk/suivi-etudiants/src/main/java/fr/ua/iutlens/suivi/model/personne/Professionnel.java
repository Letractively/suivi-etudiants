package fr.ua.iutlens.suivi.model.personne;

import java.io.Serializable;

import javax.persistence.Entity;

import fr.ua.iutlens.suivi.model.suiviPro.Entreprise;

/**
 * Entity implementation class for Entity: Professionnel
 * 
 */
@Entity
public class Professionnel extends Personne implements Serializable {

	private String poste;
	private String service;
	private static final long serialVersionUID = 1L;

	private Entreprise entreprise;

	public Professionnel() {
		super();
	}

	public String getPoste() {
		return this.poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

}