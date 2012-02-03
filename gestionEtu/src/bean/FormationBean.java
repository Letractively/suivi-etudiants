package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import util.Redirection;

import ejb.EtablissementEJB;
import ejb.FormationEJB;

import entity.Etablissement;
import entity.Formation;

@Named(value = "formationBean")
@ConversationScoped
public class FormationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Conversation conversation;
	
	@Inject
	EtablissementBean et;

	@EJB
	private FormationEJB formationEJB;

	@EJB
	private EtablissementEJB etablissementEJB;

	private Etablissement etablissement = new Etablissement();
	private Formation formation = new Formation();

	private List<Formation> formations = new ArrayList<Formation>();
	private List<Etablissement> etablissements = new ArrayList<Etablissement>();

	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private Formation editFormation;

	private List<SelectItem> etablissementsItems = new ArrayList<SelectItem>();

	private Long etablissementItemSelect;

	@PostConstruct
	public void init() {
		if (conversation.isTransient()) {
			conversation.begin();
		}

		if (this.getPassedParameter() != null) {

			Long id = Long.parseLong(this.getPassedParameter());

			formations = formationEJB.findFormationsByEtablissementId(id);
			etablissement = etablissementEJB.findEtablissementById(id);

		}

		// appel de la fonction qui initailise la liste d'item etablissement
		// pour la liste déroulante de ajoutFormation
		creerListeItem();

	}

	public List<SelectItem> creerListeItem() {
		for (Etablissement eta : etablissements) {
			etablissementsItems.add(new SelectItem(eta.getId(), eta.getNom()));
		}

		return etablissementsItems;
	}

	public void ajout() {
		this.formation.setEtablissement(etablissementEJB
				.findEtablissementById(etablissement.getId()));
		this.formationEJB.createFormation(formation);
		
		//formation.getEtablissement().getLesFormations().add(formation);

		System.out.println(formation.getEtablissement().getNom()+" : "+formation.getEtablissement().getLesFormations().size());

		conversation.end();
		//et.getConversation().isTransient();

		Redirection.listeFormations(etablissement.getId());
	}

	public void supprimer() {

		for (Formation uneFormation : formations) {
			if (checked.get(uneFormation.getId())) {
				formationEJB.removeFormation(uneFormation);
				
			}
		}
		//fin de la conversation de établissement pour mettre à jour
		//
		
		conversation.end();
		//et.getConversation().end();
		
		Redirection.listeFormations(etablissement.getId());
	}

	public void modifier() {
		formationEJB.updateFormation(editFormation);

		conversation.end();

		Redirection.listeFormations(editFormation.getEtablissement().getId());
	}

	public String edit() {

		return "editFormation";
	}

	public String getPassedParameter() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("id");
		return parametreId;
	}

	// getters and setters

	public List<Etablissement> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Long getEtablissementItemSelect() {
		return etablissementItemSelect;
	}

	public void setEtablissementItemSelect(Long etablissementItemSelect) {
		this.etablissementItemSelect = etablissementItemSelect;
	}

	public List<SelectItem> getEtablissementsItems() {
		return etablissementsItems;
	}

	public void setEtablissementsItems(List<SelectItem> etablissementsItems) {
		this.etablissementsItems = etablissementsItems;
	}

	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Formation getEditFormation() {
		return editFormation;
	}

	public void setEditFormation(Formation editFormation) {
		this.editFormation = editFormation;
	}

}
