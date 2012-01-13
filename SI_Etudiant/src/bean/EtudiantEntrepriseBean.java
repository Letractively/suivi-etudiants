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


@ManagedBean(name = "etudiantEntrepriseBean")
@RequestScoped
public class EtudiantEntrepriseBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
	
	@EJB
	private EntrepriseEJB entrepriseEJB;

	private EtudiantEntreprise etudiantEntreprise = new  EtudiantEntreprise();
	
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	
	
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();


	@PostConstruct
	public void init()  
	{  
		String id=null;
		id=getPassedParameter();
		
		if(id!=null)
		{
			entreprises = entrepriseEJB.findAllEntreprises();
			//Initialisation de la liste d'items 
			for (Entreprise ent : entreprises)
			{
				entreprisesItems.add(new SelectItem(ent.getNom()+" - "+ent.getRaisonsociale()));
			}
		}
		else
		{
			System.out.println("test");
		}

	}
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

	public EntrepriseEJB getEntrepriseEJB() {
		return entrepriseEJB;
	}

	public void setEntrepriseEJB(EntrepriseEJB entrepriseEJB) {
		this.entrepriseEJB = entrepriseEJB;
	}

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public String ajout() 
	{
		//phase de test avec des valeur par défaut (non récupérer à partir des jsf)
		
		//Définition d'un étudiant
		Etudiant etu = etudiantEJB.findEtudiantById(51L);
		System.out.println("Le nom de l'étudiant est "+etu.getNom());
		
		//Entreprise ent=entrepriseEJB.findEntrepriseById(1L);
		//System.out.println("Le nom de l'entreprise est "+ent.getNom());
		
		EtudiantEntreprise ac= new EtudiantEntreprise();
		ac.setEtudiant(etu);
		
		//System.out.println("ttttt "+ac.getId().getIdetudiant());

		return "list";
	
	}
	
	public String getPassedParameter() 
	{
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext().
		getRequestParameterMap().get("id");
		
		return parametreId;
	}
}
	