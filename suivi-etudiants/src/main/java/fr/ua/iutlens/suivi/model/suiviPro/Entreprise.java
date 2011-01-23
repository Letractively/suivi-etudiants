package fr.ua.iutlens.suivi.model.suiviPro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.personne.Professionnel;

/**
 * Entity implementation class for Entity: Entreprise
 * 
 */
@Entity
public class Entreprise extends BaseEntity implements Serializable {

	@Column(name = "nom_Entreprise", length = 64)
	private String nomEntreprise;

	@Column(name = "raison_sociale", length = 64)
	private String raisonSociale;

	@Column(name = "secteur_activite", length = 100)
	private String secteurActivite;

	@OneToMany(mappedBy = "entreprise")
	private List<Professionnel> professionnels;

	@OneToMany(mappedBy = "entreprise")
	private List<Stage> stages;

	private static final long serialVersionUID = 1L;

	public Entreprise() {
		super();
	}

	public String getNomEntreprise() {
		return this.nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getRaisonSociale() {
		return this.raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getSecteurActivite() {
		return this.secteurActivite;
	}

	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	public List<Professionnel> getProfessionnels() {
		return professionnels;
	}

	public void setProfessionnels(List<Professionnel> professionnels) {
		this.professionnels = professionnels;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	@Override
	public String getDisplayText() {
		return nomEntreprise;
	}

}