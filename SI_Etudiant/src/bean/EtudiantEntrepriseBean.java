package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import entity.Entreprise;
import entity.Etudiant;
import entity.EtudiantEntreprise;
import entity.EtudiantEntrepriseId;


@ManagedBean(name = "etudiantEntrepriseBean")
@RequestScoped
public class EtudiantEntrepriseBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
	
	@EJB
	private EntrepriseEJB entrepriseEJB;

		
	private Etudiant etudiant = new Etudiant();
	private EtudiantEntreprise etudiantEntreprise = new  EtudiantEntreprise();
	
	private List<EtudiantEntreprise> etudiantEntreprises = new ArrayList<EtudiantEntreprise>();
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	
	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	private Long entrepriseItemSelect;
		
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();


	@PostConstruct
	public void init() 
	{  
		/*
		 *  On recupere l'id passe en parametre depuis l'autre page
		 *  Attention : Il faut parser en type Long comme dans l'entite
		 */		
		if(this.getPassedParameter()!=null) 
		{
			Long id = Long.parseLong(this.getPassedParameter());
			// Je remplis ma liste d'etudiantEntreprises grace a ma requete
			etudiantEntreprises = entrepriseEJB.findCompaniesByStudentId(id);
			
			//Je recupere l'etudiant
			etudiant=etudiantEJB.findEtudiantById(id);
			
			//Je remplis la liste d'entreprise pour la page d'ajout etudiantEntreprise..	
			
			//appel de la fonction qui initailise la liste d'item entreprise
			creerListeItem();		
		}
		
		entreprises=entrepriseEJB.findAllEntreprises();	
		creerListeItem();	
		
		
		
	}
		
	public String ajout() 
	{
			
		System.out.println("Le nom de l'étudiant est "+etudiant.getNom());
		
		//System.out.println("Le numéro de l'entreprise selectionné est "+entrepriseItemSelect);
		
		//EtudiantEntrepriseId id = new EtudiantEntrepriseId(etudiantEntreprise.getDatedebut(),etudiant.getId(),entrepriseItemSelect); 
		
		return "list";
	}
	
	//création de la liste d'item Entreprise, necessaire pour la page ajouterEtudiantEntreprisexhtml
	public List<SelectItem> creerListeItem()
	{		
		for(Entreprise ent : entreprises)
		{
			//identifiant,valeur
			entreprisesItems.add(new SelectItem(ent.getId(),ent.getNom()+" "+ent.getSiret()));
		}
		
		return entreprisesItems;			
	}
	
	
	public String getPassedParameter() 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext().
		getRequestParameterMap().get("id");
		return parametreId;
	}
	
	
	//getter and setter
	
	public EtudiantEntreprise getEtudiantEntreprise() {
		return etudiantEntreprise;
	}
	public void setEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise) {
		this.etudiantEntreprise = etudiantEntreprise;
	}
	public List<SelectItem> getEntreprisesItems() {
		return entreprisesItems;
	}
	public void setEntreprisesItems(List<SelectItem> entreprisesItems) {
		this.entreprisesItems = entreprisesItems;
	}
	public List<EtudiantEntreprise> getEtudiantEntreprises() {
		return etudiantEntreprises;
	}
	public void setEtudiantEntreprises(List<EtudiantEntreprise> etudiantEntreprises) {
		this.etudiantEntreprises = etudiantEntreprises;
	}
	public List<Entreprise> getEntreprises() {
		return entreprises;
	}
	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Long getEntrepriseItemSelect() {
		return entrepriseItemSelect;
	}
	public void setEntrepriseItemSelect(Long entrepriseItemSelect) {
		this.entrepriseItemSelect = entrepriseItemSelect;
	}
	
	
	
}
	