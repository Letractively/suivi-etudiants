package fr.ua.iutlens.suivi.model.personne;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.suivipro.Stage;

/**
 * Entity implementation class for Entity: Enseignant
 * 
 */
@Entity
public class Enseignant extends Personne implements Serializable {

	// champs statut (???)
	private String statut;

	private static final long serialVersionUID = 1L;

	// Un enseignant s'occupe d'une liste de stages ( 0 à n stages)
	// mappedBy --> Le champ qui possède la relation. Obligatoire 
	// sauf si la relation est unidirectionnelle.
	@OneToMany(mappedBy = "enseignant")
	private List<Stage> stages;

	public Enseignant() {
		super();
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

}