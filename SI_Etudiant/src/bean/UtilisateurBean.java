package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Any;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import util.MD5Password;
import ejb.UtilisateurEJB;
import entity.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@RequestScoped
public class UtilisateurBean {

	@EJB
	private UtilisateurEJB utilisateurEJB;

	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private Utilisateur utilisateur = new Utilisateur();

	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	private List<SelectItem> niveauItems = new ArrayList<SelectItem>();
	private String niveauItemSelect;

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

	public void supprimer() {
		for (Utilisateur unUtilisateur : utilisateurs) {
			if (checked.get(unUtilisateur.getId())) {
				utilisateurEJB.removeUtilisateur(unUtilisateur);
			}
		}
	}

	public String ajout() {

		String mdpMD5 = MD5Password.getEncodedPassword(utilisateur
				.getMotDePasse());

		this.utilisateur.setMotDePasse(mdpMD5);
		this.utilisateur.setNiveau(niveauItemSelect);

		this.utilisateurEJB.createUtilisateur(utilisateur);

		return "listeUser";
	}
	
	public void modifier() {
		
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

}
