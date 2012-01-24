package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.EntrepriseEJB;
import entity.Entreprise;

//named : propre à conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "entrepriseBean")
@ConversationScoped
public class EntrepriseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * J'ai du ajouter le fichier beans.xml pour que la conversation fonctionne
	 * voir :
	 * 
	 * http://miageprojet2.unice.fr/Intranet_de_Michel_Buffa/
	 * Cours_composants_distribu%C3%A9s _pour_l
	 * 'entreprise_%2F%2F_EJB_2009/FAQ_JSF2_%3A_solutions_aux_erreurs_les_plus_courantes
	 * 
	 * d'ailleur l'absence de implements serializable fait buggué le projet,
	 * impossible de le lancer
	 * 
	 * l’annotation @Inject permet l’injection et se base sur le type et non sur
	 * des expressions EL comme l’annotation @ManagedProperty utilisée avec
	 * l’annotation @ManagedBean.
	 * 
	 * Donc
	 * 
	 * Inject si on est en named
	 * 
	 * Managed property si on est en managedBean
	 * 
	 * Inject permet d'injecter un autre bean
	 * 
	 * exemple : @inject EntrepriseBean entrepriseBean
	 */
	@Inject
	Conversation conversation;

	@EJB
	private EntrepriseEJB entrepriseEJB;

	private Entreprise entreprise = new Entreprise();

	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Entreprise editEntreprise;// on instancie pas, c'est l'entreprise
										// que l'on rï¿½cupere ï¿½ partir du jsf

	@PostConstruct
	public void init() {
		conversation.begin();
		entreprises = entrepriseEJB.findAllEntreprises();
	}

	public String ajout() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entreprise ajoutée"));
		this.entrepriseEJB.createEntreprise(entreprise);

		conversation.end();

		/*
		 * si on est en session, actualisation du la liste d'entreprises et de
		 * entreprise entreprises = entrepriseEJB.findAllEntreprises();
		 * entreprise =new Entreprise();
		 */

		return "listeEntreprise";
	}

	public void supprimer() {
		for (Entreprise uneEntreprise : entreprises) {
			if (checked.get(uneEntreprise.getId())) {
				entrepriseEJB.removeEntreprise(uneEntreprise);
			}
		}
		// si on est en session, actualisation de la liste d'entreprises
		entreprises = entrepriseEJB.findAllEntreprises();
	}

	public String modifier() {
		entrepriseEJB.updateEntreprise(editEntreprise);

		conversation.end();

		/*
		 * si on est en session, actualisation de la liste d'entreprise
		 * entreprises=entrepriseEJB.findAllEntreprises();
		 */

		return "listeEntreprise";
	}

	public String edit() {
		return "editEntreprise";
	}

	// getter and setter

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEtudiants(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Entreprise getEditEntreprise() {
		return editEntreprise;
	}

	public void setEditEntreprise(Entreprise editEntreprise) {
		this.editEntreprise = editEntreprise;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

}
