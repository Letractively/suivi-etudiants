package fr.ua.iutlens.suivi.model.suivipro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.ContactEntreprise;
import fr.ua.iutlens.suivi.model.personne.Professionnel;

/**
 * Entity implementation class for Entity: Entreprise
 * 
 */
@Entity
public class Entreprise extends BaseEntity implements Serializable {

	// champs nom_entreprise, raison_sociale, secteur_activite
	@Column(name = "nom_entreprise", length = 64)
	private String nomEntreprise;
	@Column(name = "raison_sociale", length = 64)
	private String raisonSociale;
	@Column(name = "secteur_activite", length = 100)
	private String secteurActivite;

	
	// l'entreprise possède une liste de professionnels
	@OneToMany(mappedBy = "entreprise")
	private List<Professionnel> professionnels;

	// l'entreprise possède une liste de stages
	@OneToMany(mappedBy = "entreprise")
	private List<Stage> stages;

	// l'entreprise possède une liste d'adresses de contact
	@OneToMany(mappedBy = "entreprise")
	private List<ContactEntreprise> contactEntreprises;


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

	public void setContactEntreprises(List<ContactEntreprise> contactEntreprises) {
		this.contactEntreprises = contactEntreprises;
	}

	public List<ContactEntreprise> getContactEntreprises() {
		return contactEntreprises;
	}

	@Override
	public String getDisplayText() {
		return nomEntreprise;
	}

}