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

	// champs login (unique, non null, taille max 50caractères)
	@Column(unique = true, nullable = false, length = 50)
	private String login;
	
	// champs mot de passe
	private String mdp;
	
	// champs role de l'utilisateur (admin, secrétaire, enseignant, etudiant, ...)
	private String role;
	
	// clé étrangère ???
	// option fetch :
	// La stratégie EAGER est une exigence sur le runtime fournisseur de persistance que l'entité associée doit être chargée agressivement.
	// La stratégie LAZY est une allusion à l'exécution fournisseur de persistance.
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
	// Indique qu'une déclaration de méthode est destinée à remplacer une déclaration de méthode dans
	// une superclasse. Si une méthode est annoté avec ce type d'annotation mais ne remplace pas une 
	// méthode de classe, les compilateurs sont nécessaires pour générer un message d'erreur
	@Override
	public String getDisplayText() {
		return login;
	}
}