package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import util.Redirection;

import ejb.EtablissementEJB;
import entity.Etablissement;
import entity.Etudiant;

//named : propre ï¿½ conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "etablissementBean")
@ConversationScoped
public class EtablissementBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Conversation conversation;

	@EJB
	private EtablissementEJB etablissementEJB;

	private Etablissement etablissement = new Etablissement();

	private List<Etablissement> etablissements = new ArrayList<Etablissement>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Etablissement editEtablissement;// on instancie pas, c'est
											// l'etablissement que l'on
											// recupere a partir du jsf

	private Etablissement selectedEtablissement;

	private List<SelectItem> typeEtablissementItems = new ArrayList<SelectItem>();
	private String typeEtablissementSelected;

	@PostConstruct
	public void init() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
		try {
			etablissements = etablissementEJB.findAllEtablissements();
			creerListeTypeEtablissement();

		} catch (EJBException e) {

			Redirection.erreurXhtml();
		}
	}

	public String ajout() {

		this.etablissement.setTypeEtab(typeEtablissementSelected);

		this.etablissementEJB.createEtablissement(etablissement);

		conversation.end();
		Redirection.listeEtablissements();
		return "listeEtablissement";
	}

	public void supprimer() {
		for (Etablissement uneEtablissement : etablissements) {
			if (checked.get(uneEtablissement.getId())) {
				etablissementEJB.removeEtablissement(uneEtablissement);
			}
		}
		etablissements = etablissementEJB.findAllEtablissements();
	}

	public String modifier() {
		this.editEtablissement.setTypeEtab(typeEtablissementSelected);

		etablissementEJB.updateEtablissement(editEtablissement);

		conversation.end();

		return "listeEtablissement";
	}

	public List<SelectItem> creerListeTypeEtablissement() {

		typeEtablissementItems.add(new SelectItem("Lycee", "Lycee"));
		typeEtablissementItems.add(new SelectItem("IUT", "IUT"));
		typeEtablissementItems.add(new SelectItem("Faculté", "Faculté"));

		return typeEtablissementItems;
	}

	public String edit() {
		return "editEtablissement";
	}

	// getters and setters

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public List<Etablissement> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}

	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Etablissement getEditEtablissement() {
		return editEtablissement;
	}

	public void setEditEtablissement(Etablissement editEtablissement) {
		this.editEtablissement = editEtablissement;
	}

	public Etablissement getSelectedEtablissement() {
		return selectedEtablissement;
	}

	public void setSelectedEtablissement(Etablissement selectedEtablissement) {
		this.selectedEtablissement = selectedEtablissement;
	}

	public List<SelectItem> getTypeEtablissementItems() {
		return typeEtablissementItems;
	}

	public void setTypeEtablissementItems(
			List<SelectItem> typeEtablissementItems) {
		this.typeEtablissementItems = typeEtablissementItems;
	}

	public String getTypeEtablissementSelected() {
		return typeEtablissementSelected;
	}

	public void setTypeEtablissementSelected(String typeEtablissementSelected) {
		this.typeEtablissementSelected = typeEtablissementSelected;
	}

}
