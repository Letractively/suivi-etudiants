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
@Inheritance(strategy = TABLE_PER_CLASS)
public class Contact extends BaseEntity implements Serializable {

	// @Id
	// private Integer idContact;
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

	public Contact() {
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

	@Override
	public String getDisplayText() {
		return adresse + ville + codepostal;
	}

}