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
import fr.ua.iutlens.suivi.model.Contact;

/**
 * Entity implementation class for Entity: Personne
 * 
 */
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class Personne extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 50)
	private String nomPersonne;
	@Column(length = 50, nullable = false)
	private String prenomPersonne;
	@Column(nullable = false, length = 15)
	private String civilite;
	@Basic(fetch = LAZY)
	@Lob
	private byte[] photo;
	
	@JoinColumn(name = "id_cible")
	@OneToMany
	private List<Contact> contacts;
	
	@OneToMany(mappedBy = "personne")
	private List<Utilisateur> utilisateurs;

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

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
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

}