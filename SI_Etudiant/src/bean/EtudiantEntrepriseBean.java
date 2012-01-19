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

import util.Redirection;

import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import ejb.EtudiantEntrepriseEJB;
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
	
	@EJB
	private EtudiantEntrepriseEJB etudiantEntrepriseEJB;

		
	private Etudiant etudiantEnt = new Etudiant();
	
	private EtudiantEntreprise etudiantEntreprise = new  EtudiantEntreprise();
	private EtudiantEntrepriseId etudiantEntrepriseId = new  EtudiantEntrepriseId();
	
	private List<EtudiantEntreprise> etudiantEntreprises = new ArrayList<EtudiantEntreprise>();
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	
	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	//c'est cette variable qui aura l'id de lentreprise selectionné via la liste déroulante 
	private Long entrepriseItemSelect;
		
	private HashMap<EtudiantEntrepriseId, Boolean> checked = new HashMap<EtudiantEntrepriseId, Boolean>();


	@PostConstruct
	public void init() 
	{  
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
				etudiantEntreprises = etudiantEntrepriseEJB.findCompaniesByStudentId(id);
			

				//Je recupere l'etudiant
				etudiantEnt=etudiantEJB.findEtudiantById(id);
					
				//Je recupere l'etudiant
				
			}
			catch(NumberFormatException e)
			{
				Redirection.erreurXhtml();
			}

		}
		//Je remplis la liste d'entreprise pour la page d'ajout etudiantEntreprise..
		entreprises=entrepriseEJB.findAllEntreprises();	
		
		//appel de la fonction qui initailise la liste d'item entreprise
		creerListeItem();	
		
		
		
	}
		
	public String ajout() 
	{
		//pour recuper l'id de l'etudiant, j'ai mis un champ caché dans le formulaire jsf pour faire le traitement ici, dans la fonction

		System.out.println("Le numero de l'étudiant est "+etudiantEnt.getNom());

		Long idEtudiant=etudiantEnt.getId();		
		
		System.out.println("Le numéro de l'entreprise selectionné est "+entrepriseItemSelect);
		
		/*etudiantEntrepriseId = new EtudiantEntrepriseId(etudiantEntrepriseId.getDatedebut(),idEtudiant,entrepriseItemSelect);
		
		
		//mise en place de la clé primaire
		etudiantEntreprise.setId(etudiantEntrepriseId);
		
		//On prévient l'objet. Si cette instruction n'est pas présente le nom n'est pas rafraichi dans le tableau etudiantEntreprise
		etudiantEntreprise.setEntreprise(entrepriseEJB.findEntrepriseById(entrepriseItemSelect));
				
		//On ajoute étudiantEntrepise dans la BDD
		etudiantEntrepriseEJB.createEtudiantEntreprise(etudiantEntreprise);
		
		//redirection vers la liste des activités. Seul solution trouvé pour passé l'id en parametre
		Redirection.listeEtudiantEntreprise(idEtudiant);

		*/
		return "listeEtudiant";

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
	
	public String supprimer()
	{
		
				
		for (EtudiantEntreprise unEtudiantEntreprise : etudiantEntreprises )
        {
			System.out.println("Test");
			
			if (checked.get(unEtudiantEntreprise.getId())) 
            {
				System.out.println("Test");
				//etudiantEntrepriseEJB.removeEtudiantEntreprise(unEtudiantEntreprise);	
            }
        }
		
		return "test";
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
	public Etudiant getEtudiantEnt() {
		return etudiantEnt;
	}
	public void setEtudiantEnt(Etudiant etudiant) {
		this.etudiantEnt = etudiant;
	}
	public Long getEntrepriseItemSelect() {
		return entrepriseItemSelect;
	}
	public void setEntrepriseItemSelect(Long entrepriseItemSelect) {
		this.entrepriseItemSelect = entrepriseItemSelect;
	}
	public EtudiantEntrepriseId getEtudiantEntrepriseId() {
		return etudiantEntrepriseId;
	}
	public void setEtudiantEntrepriseId(EtudiantEntrepriseId etudiantEntrepriseId) {
		this.etudiantEntrepriseId = etudiantEntrepriseId;
	}

	public HashMap<EtudiantEntrepriseId, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<EtudiantEntrepriseId, Boolean> checked) {
		this.checked = checked;
	}
	
	
	
}
	
	