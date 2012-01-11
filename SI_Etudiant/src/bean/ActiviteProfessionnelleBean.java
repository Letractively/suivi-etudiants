package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import entity.ActiviteProfessionnelle;
import entity.Entreprise;


@ManagedBean(name = "activiteProBean")
@RequestScoped
public class ActiviteProfessionnelleBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
	
	@EJB
	private EntrepriseEJB entrepriseEJB;

	private ActiviteProfessionnelle actionPro = new  ActiviteProfessionnelle();
	
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	
	
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();


	@PostConstruct
	public void init() 
	{  
		entreprises = entrepriseEJB.findAllEntreprises();
		
		//Initialisation de la liste d'items 
		for (Entreprise ent : entreprises)
		{
			entreprisesItems.add(new SelectItem(ent.getNom()+" - "+ent.getRaisonsociale()));
		}
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


	public ActiviteProfessionnelle getActionPro() {
		return actionPro;
	}


	public void setActionPro(ActiviteProfessionnelle actionPro) {
		this.actionPro = actionPro;
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
		/*Etudiant etu = etudiantEJB.findEtudiantById(2L);
		System.out.println("Le nom de l'étudiant est "+etu.getNom());
		
		Entreprise ent=entrepriseEJB.findEntrepriseById(1L);
		System.out.println("Le nom de l'entreprise est "+ent.getNom());
		
		EtudiantEntreprisePK actionProPK= new EtudiantEntreprisePK();
		*/
		//actionProPK .setIdentreprise(1L);
		//actionProPK.setIdentreprise(2L);
		
		//actionPro.setId(actionProPK);
		
		//System.out.println("Action Pro : "+actionPro.getEtudiant().getNom());
		
		
		return "list";
	
	}
}
	