package fr.ua.iutlens.suivi.model.personne;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Utilisateur
 * 
 */
@Entity
public class Utilisateur extends BaseEntity implements Serializable {

	@Column(unique = true, nullable = false, length = 50)
	private String login;
	private String mdp;
	@ManyToOne(fetch = LAZY)
	private Personne personne;
	private String role;
	private static final long serialVersionUID = 1L;

	public Utilisateur() {
		super();
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getrole() {
		return this.role;
	}

	public void setrole(String role) {
		this.role = role;
	}

	@Override
	public String getDisplayText() {
		return login;
	}
}