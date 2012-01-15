package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;

import ejb.EntrepriseEJB;
import entity.Entreprise;

@ManagedBean(name = "entrepriseBean")
@SessionScoped
public class EntrepriseBean {
	@EJB
	private EntrepriseEJB entrepriseEJB;
  
	private Entreprise entreprise =new Entreprise();
	
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Entreprise editEntreprise;//on instancie pas, c'est l'entreprise que l'on r�cupere � partir du jsf 
	
	@PostConstruct
	public void init() 
	{
	    entreprises = entrepriseEJB.findAllEntreprises();
	}	  

	public String ajout() 
	{
		this.entrepriseEJB.createEntreprise(entreprise);  
		
		//si on est en session, actualisation du la liste d'entreprises et de entreprise
		entreprises = entrepriseEJB.findAllEntreprises();
		entreprise =new Entreprise();
		  
		return "listeEntreprise";
	}
	
	public void supprimer() 
	{
		List<Entreprise> entrepriseSelectionne = new ArrayList<Entreprise>();
			
		System.out.println("Test !!!!!!!!!");
		for (Entreprise uneEntreprise : entreprises)
		{
			if (checked.get(uneEntreprise.getId()))
			{
				  entrepriseEJB.removeEntreprise(uneEntreprise);
			}
		}
		//si on est en session, actualisation de la liste d'entreprises
		entreprises=entrepriseEJB.findAllEntreprises();
	}
	
	public String modifier() 
	{
		entrepriseEJB.updateEntreprise(editEntreprise);
		
		//si on est en session, actualisation de la liste d'entreprise
		entreprises=entrepriseEJB.findAllEntreprises();
		  
		return "listeEntreprise";
	}
	  
	public String edit()
	{
		System.out.println(editEntreprise.getId());	
		return "editEntreprise";
	}
	  
	  
	  //getter and setter
	  
	public Entreprise getEntreprise() 
	{
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) 
	{
		this.entreprise = entreprise;
	}
	public List<Entreprise> getEntreprises() 
	{
		return entreprises;
	}
	public void setEtudiants(List<Entreprise> entreprises) 
	{
		this.entreprises = entreprises;
	}
	public HashMap<Long, Boolean> getChecked() 
	{
		return checked;
	}
	public void setChecked(HashMap<Long, Boolean> checked) 
	{
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
