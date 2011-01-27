package fr.ua.iutlens.suivi.model.personne;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.suivipro.Entreprise;
import fr.ua.iutlens.suivi.model.suivipro.Stage;

/**
 * Entity implementation class for Entity: Professionnel
 * 
 */
@Entity
public class Professionnel extends Personne implements Serializable {

	// champs poste occupé par le pro
	private String poste;
	// champs service (service informatique, service commerciale, ...)
	private String service;
	
	private static final long serialVersionUID = 1L;

	// clé étrangère : un professionnel appartient à une entreprise
	private Entreprise entreprise;

	// un professionnel est le tuteur d'une liste de stage (0 à n)
	@OneToMany(mappedBy = "professionnel")
	private List<Stage> stages;

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

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

}