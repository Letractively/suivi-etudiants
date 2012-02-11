package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import util.CryptageSHA256;
import util.MD5Password;
import util.Mail;
import util.MotDePasseAleatoire;
import util.Redirection;
import ejb.UtilisateurEJB;
import entity.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@ViewScoped
public class UtilisateurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// récupération du bean login
	@ManagedProperty(value = "#{login}")
	private Login login;

	@EJB
	private UtilisateurEJB utilisateurEJB;

	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private Utilisateur utilisateur = new Utilisateur();

	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	private List<SelectItem> niveauItems = new ArrayList<SelectItem>();
	private String niveauItemSelect;
	
	private Utilisateur editUtilisateur;

	@PostConstruct
	public void init() {
		utilisateurs = utilisateurEJB.findAllUtilisateurs();

		creerListeItemEntreprise();
	}

	public List<SelectItem> creerListeItemEntreprise() {
		niveauItems.add(new SelectItem("Lecture"));
		niveauItems.add(new SelectItem("Administrateur"));

		return niveauItems;
	}

	public String supprimer() {

		for (Utilisateur unUtilisateur : utilisateurs) {

			if (checked.get(unUtilisateur.getId())) {
				utilisateurEJB.removeUtilisateur(unUtilisateur);

			}
		}
		return "listeUser";
	}

	public String ajout() {
		/*
		 * Envoi du mail -- Ok Le faire avant de crypter le mot de passe sinon
		 * l'utilisateur va recevoir le hash MD5 et pas son vrai mot de passe !
		 */
		Mail.autoMail(utilisateur.getMail(), utilisateur.getMotDePasse());

		String mdpSHA256 = CryptageSHA256.crypter(utilisateur
				.getMotDePasse());

		this.utilisateur.setMotDePasse(mdpSHA256);
		
		/*
		 * Changer la chaine niveauItemSelect pour la BDD
		 */
		if(niveauItemSelect.equals("Lecture")) {
			niveauItemSelect = "2";
		}
		else {
			if(niveauItemSelect.equals("Administrateur")) {
				niveauItemSelect = "1";
			}
		}
		
		this.utilisateur.setNiveau(niveauItemSelect);
		
		this.utilisateurEJB.createUtilisateur(utilisateur);

		Redirection.listeUtilisateurs();
		return "listeUser";
	}

	public void modifierCompte() {
		// Message de modif
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Votre mot de passe a bien été modifié"));
		
		//Mail.autoMail(utilisateur.getMail(), utilisateur.getMotDePasse());
		

		// recuperation de la session
		Utilisateur user = login.getUtilisateur();

		String mdpMD5 = CryptageSHA256.crypter(utilisateur
				.getMotDePasse());

		// modification du mot de passe : on met le mot de passe de utilisateur
		// dans user
		user.setMotDePasse(mdpMD5);

		// on persiste dans la bd
		utilisateurEJB.updateUtilisateur(user);

		// déconnexion automatique en appelant la fonction logout
		//login.logout();

	}
	public String modifier() {
		utilisateurEJB.updateUtilisateur(editUtilisateur);
		return"test";
	}

	public String genererMDP() {
		return MotDePasseAleatoire.genererMotDePasse();
	}

	// getters and setters
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<SelectItem> getNiveauItems() {
		return niveauItems;
	}

	public void setNiveauItems(List<SelectItem> niveauItems) {
		this.niveauItems = niveauItems;
	}

	public String getNiveauItemSelect() {
		return niveauItemSelect;
	}

	public void setNiveauItemSelect(String niveauItemSelect) {
		this.niveauItemSelect = niveauItemSelect;
	}

	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Utilisateur getEditUtilisateur() {
		return editUtilisateur;
	}

	public void setEditUtilisateur(Utilisateur editUtilisateur) {
		this.editUtilisateur = editUtilisateur;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
