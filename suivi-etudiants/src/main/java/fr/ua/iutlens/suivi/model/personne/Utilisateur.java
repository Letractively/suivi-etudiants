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

	// champs login (unique, non null, taille max 50caract�res)
	@Column(unique = true, nullable = false, length = 50)
	private String login;
	
	// champs mot de passe
	private String mdp;
	
	// champs role de l'utilisateur (admin, secr�taire, enseignant, etudiant, ...)
	private String role;
	
	// cl� �trang�re ???
	// option fetch :
	// La strat�gie EAGER est une exigence sur le runtime fournisseur de persistance que l'entit� associ�e doit �tre charg�e agressivement.
	// La strat�gie LAZY est une allusion � l'ex�cution fournisseur de persistance.
	@ManyToOne(fetch = LAZY)
	private Personne personne;
	
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

	// Override :
	// Indique qu'une d�claration de m�thode est destin�e � remplacer une d�claration de m�thode dans
	// une superclasse. Si une m�thode est annot� avec ce type d'annotation mais ne remplace pas une 
	// m�thode de classe, les compilateurs sont n�cessaires pour g�n�rer un message d'erreur
	@Override
	public String getDisplayText() {
		return login;
	}
}