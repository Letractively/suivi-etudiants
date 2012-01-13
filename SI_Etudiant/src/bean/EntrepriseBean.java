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
	
	public String doNewEntreprise() 
	{
		return "newEntreprise.xhtml"; 
	}
	
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

	public String ajout() 
	{
		  
		//System.out.println(entreprise.getContact().getMail());
		/*this.entrepriseEJB.createEntreprise(entreprise);  
		  
		  //si on est en session, sinon pas besoin...
		  entreprises = entrepriseEJB.findAllEntreprises();
		  entreprise =new Entreprise();*/
		  
		  return "x";
	  }
	  public void supprimer() 
	  {
		  	System.out.println("Test !!!!!!!!!");
	        List<Entreprise> entrepriseSelectionne = new ArrayList<Entreprise>();

	        System.out.println("Test !!!!!!!!!");
	        for (Entreprise uneEntreprise : entreprises)
	        {
	            if (checked.get(uneEntreprise.getId())) 
	            if (checked.get(uneEntreprise.getId()))
	            {
	            	 System.out.println(uneEntreprise.getNom());
	            	
	            	//System.out.println("Selection : "+unEtudiant.getNom());
	            	
	            	 entrepriseEJB.removeEntreprise(uneEntreprise);
	            	
	            }
	        }
	        entreprises=entrepriseEJB.findAllEntreprises();
	        
	   }
	  public String edit()
	  {
		  System.out.println(editEntreprise.getId());	
		  return "editEntreprise";
			
	  }
	  public String modifier() 
	  {
		  entrepriseEJB.updateEntreprise(editEntreprise);
		  
		  entreprises=entrepriseEJB.findAllEntreprises();
		  
		  return "listeEntreprise";
	  }

}
