package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import util.Redirection;

import ejb.EtablissementEJB;
import ejb.FormationEJB;

import entity.Etablissement;
import entity.Formation;

//named : propre � conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "formationBean")  
@ConversationScoped 
@ViewScoped
public class FormationBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject  
	Conversation conversation;  
	
	@EJB
	private FormationEJB formationEJB;
	
	@EJB
	private EtablissementEJB etablissementEJB;
	
	private Etablissement etablissement = new Etablissement();
	
	private Formation formation = new  Formation();
	
	private List<Formation> formations = new ArrayList<Formation>();
	private List<Etablissement> etablissements = new ArrayList<Etablissement>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Formation editFormation;
	
	private List<SelectItem> etablissementsItems = new ArrayList<SelectItem>();
	//c'est cette variable qui aura l'id de lentreprise selectionn� via la liste d�roulante 
	private Long etablissementItemSelect;
	
	@PostConstruct
	public void init() 
	{  
		conversation.begin();

		/*
		 *  On recupere l'id passe en parametre depuis l'autre page
		 *  Attention : Il faut parser en type Long comme dans l'entite
		 */		
		if(this.getPassedParameter()!=null) 
		{
			try
			{
				Long id = Long.parseLong(this.getPassedParameter());
				// Je remplis ma liste d'etudiantEntreprises grace a ma requete
				formations = formationEJB.findFormationsByEtablissementId(id);
				
			

				//Je recupere l'etudiant
				etablissement = etablissementEJB.findEtablissementById(id);
					
				
			}
			catch(NumberFormatException e)
			{
				Redirection.erreurXhtml();
			}

		}
		//Je remplis la liste d'entreprise pour la page d'ajout etudiantEntreprise..
		etablissements=etablissementEJB.findAllEtablissements();
		
		//appel de la fonction qui initailise la liste d'item entreprise
		creerListeItem();	
		
		
		
	}
	
	public String ajout() 
	{
		//pour recuper l'id de l'etudiant, j'ai mis un champ cach� dans le formulaire jsf pour faire le traitement ici, dans la fonction


		Long idEtablissement=etablissement.getId();
		this.formation.setEtablissement(etablissementEJB.findEtablissementById(idEtablissement));		
		this.formationEJB.createFormation(formation);  
		Redirection.listeFormations(idEtablissement);

		/*si on est en session, actualisation du la liste d'entreprises et de entreprise
		entreprises = entrepriseEJB.findAllEntreprises();
		entreprise =new Entreprise();*/
		  
		return "listeEtablissement";
		
	}
	
	public List<SelectItem> creerListeItem()
	{		
		for(Etablissement eta : etablissements)
		{
			//identifiant,valeur
			etablissementsItems.add(new SelectItem(eta.getId(),eta.getNom()));
		}
		
		return etablissementsItems;			
	}
	
	public String getPassedParameter() 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext().
		getRequestParameterMap().get("id");
		return parametreId;
	}
	
	public String supprimer()
	{
		
				
		for (Formation uneFormation : formations )
        {
			
			if (checked.get(uneFormation.getId())) 
            {
				formationEJB.removeFormation(uneFormation);
            }
        }
		
		return "test";
	}
	
	public String edit()
	{
		return "editFormation";
	}
	
	public String modifier() 
	{
		formationEJB.updateFormation(editFormation);
		
		conversation.end();  
		
		/*si on est en session, actualisation de la liste d'entreprise
		entreprises=entrepriseEJB.findAllEntreprises();*/
		  
		return "listeEtablissement";
	}

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
