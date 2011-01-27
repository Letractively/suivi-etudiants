package fr.ua.iutlens.suivi.model.personne;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.ContactPersonne;
import fr.ua.iutlens.suivi.model.suivipro.SuiviStage;

/**
 * Entity implementation class for Entity: Personne
 * 
 */
@Entity

// Inherite
// Définit la stratégie d'héritage à utiliser pour une hiérarchie de classe d'entité.
// Il est précisé sur la classe d'entité qui est la racine de la hiérarchie des classes d'entité.
// Si l'annotation héritage n'est pas spécifié ou si aucun type d'héritage est spécifié pour 
// une hiérarchie de classe d'entité, la stratégie de mapping SINGLE_TABLE est utilisé.
@Inheritance(strategy = TABLE_PER_CLASS)

public class Personne extends BaseEntity implements Serializable {

	// champs nom, prénom de la personne et sa civilité(non null)
	@Column(nullable = false, length = 50)
	private String nomPersonne;
	@Column(length = 50, nullable = false)
	private String prenomPersonne;
	@Column(nullable = false, length = 15)
	private String civilite;
	
	// champs photo
	
	//	@ BASIC : Le type le plus simple de mappage à une colonne de base de données. 
	//	L'annotation de base peut être appliquée à une propriété persistante ou variable 
	//	d'instance de l'un des types suivants(string, date,...) 
	@Basic(fetch = LAZY)
	// @lob	Spécifie qu'une propriété persistante ou le champ 
	//  doit être sauvegardé comme un objet de grande taille à un type d'objet database
	//	volumineux  pris en charge.
	@Lob
	private byte[] photo;

	
	// une personne possède une liste d'adresse de contact
	@JoinColumn(name = "id_Personne")
	@OneToMany
	private List<ContactPersonne> contactPersonnes;

	// une personne peut être plusieurs utilisateurs différents
	@OneToMany(mappedBy = "personne")
	private List<Utilisateur> utilisateurs;

	// une personne est rédacteur de 0 à n suivi de stages
	@OneToMany(mappedBy = "personne")
	private List<SuiviStage> suiviStages;

	private static final long serialVersionUID = 1L;

	public Personne() {
		super();
	}

	public String getNomPersonne() {
		return this.nomPersonne;
	}

	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	public String getPrenomPersonne() {
		return this.prenomPersonne;
	}

	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}

	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public List<ContactPersonne> getContacts() {
		return contactPersonnes;
	}

	public void setContacts(List<ContactPersonne> contactPersonnes) {
		this.contactPersonnes = contactPersonnes;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@Override
	public String getDisplayText() {
		return nomPersonne + prenomPersonne;
	}

	public List<ContactPersonne> getContactPersonnes() {
		return contactPersonnes;
	}

	public void setContactPersonnes(List<ContactPersonne> contactPersonnes) {
		this.contactPersonnes = contactPersonnes;
	}

	public List<SuiviStage> getSuiviStages() {
		return suiviStages;
	}

	public void setSuiviStages(List<SuiviStage> suiviStages) {
		this.suiviStages = suiviStages;
	}

}