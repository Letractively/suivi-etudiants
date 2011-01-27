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
// D�finit la strat�gie d'h�ritage � utiliser pour une hi�rarchie de classe d'entit�.
// Il est pr�cis� sur la classe d'entit� qui est la racine de la hi�rarchie des classes d'entit�.
// Si l'annotation h�ritage n'est pas sp�cifi� ou si aucun type d'h�ritage est sp�cifi� pour 
// une hi�rarchie de classe d'entit�, la strat�gie de mapping SINGLE_TABLE est utilis�.
@Inheritance(strategy = TABLE_PER_CLASS)

public class Personne extends BaseEntity implements Serializable {

	// champs nom, pr�nom de la personne et sa civilit�(non null)
	@Column(nullable = false, length = 50)
	private String nomPersonne;
	@Column(length = 50, nullable = false)
	private String prenomPersonne;
	@Column(nullable = false, length = 15)
	private String civilite;
	
	// champs photo
	
	//	@ BASIC : Le type le plus simple de mappage � une colonne de base de donn�es. 
	//	L'annotation de base peut �tre appliqu�e � une propri�t� persistante ou variable 
	//	d'instance de l'un des types suivants(string, date,...) 
	@Basic(fetch = LAZY)
	// @lob	Sp�cifie qu'une propri�t� persistante ou le champ 
	//  doit �tre sauvegard� comme un objet de grande taille � un type d'objet database
	//	volumineux  pris en charge.
	@Lob
	private byte[] photo;

	
	// une personne poss�de une liste d'adresse de contact
	@JoinColumn(name = "id_Personne")
	@OneToMany
	private List<ContactPersonne> contactPersonnes;

	// une personne peut �tre plusieurs utilisateurs diff�rents
	@OneToMany(mappedBy = "personne")
	private List<Utilisateur> utilisateurs;

	// une personne est r�dacteur de 0 � n suivi de stages
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