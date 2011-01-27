package fr.ua.iutlens.suivi.model;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 * Entity implementation class for Entity: contact
 * 
 */
@Entity

// Inherite
// D�finit la strat�gie d'h�ritage � utiliser pour une hi�rarchie de classe d'entit�.
// Il est pr�cis� sur la classe d'entit� qui est la racine de la hi�rarchie des classes d'entit�.
// Si l'annotation h�ritage n'est pas sp�cifi� ou si aucun type d'h�ritage est sp�cifi� pour 
// une hi�rarchie de classe d'entit�, la strat�gie de mapping SINGLE_TABLE est utilis�.
@Inheritance(strategy = TABLE_PER_CLASS)
public class ContactPersonne extends BaseEntity implements Serializable {

	// @Id
	// private Integer idContact;
	
	// champs de l'adresse
	private String numero;
	private String adresse;
	private String codepostal;
	private String ville;
	private String mail;
	private String telfixe;
	private String telport;
	
	// private Integer idPers;
	// private Integer idEnt;
	// private Integer idEtab;
	
	private static final long serialVersionUID = 1L;

	public ContactPersonne() {
		super();
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelfixe() {
		return this.telfixe;
	}

	public void setTelfixe(String telfixe) {
		this.telfixe = telfixe;
	}

	public String getTelport() {
		return this.telport;
	}

	public void setTelport(String telport) {
		this.telport = telport;
	}

	// Override :
	// Indique qu'une d�claration de m�thode est destin�e � remplacer une d�claration de m�thode dans
	// une superclasse. Si une m�thode est annot� avec ce type d'annotation mais ne remplace pas une 
	// m�thode de classe, les compilateurs sont n�cessaires pour g�n�rer un message d'erreur
	@Override
	public String getDisplayText() {
		return adresse + ville + codepostal;
	}

}