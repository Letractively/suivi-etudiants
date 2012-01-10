package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	public String ajout() 
	  {
		
		
		/*  System.out.println("nom : "+etudiant.getId()+"-----"+etudiant.getNom()+"prenom : "+
		 * etudiant.getPrenom()+" mail : "+etudiant.getMail()+" adresse :"+etudiant.getAdresse()+
		 * "cp : "+etudiant.getCodepostal()+"tel :" +
			etudiant.getTel());*/	  
		  this.entrepriseEJB.createEntreprise(entreprise);  
		  
		  //si on est en session, sinon pas besoin...
		  entreprises = entrepriseEJB.findAllEntreprises();
		  entreprise =new Entreprise();
		  
		  return "listeEntreprise";
	  }
	  public void supprimer() 
	  {
		  	System.out.println("Test !!!!!!!!!");
	        List<Entreprise> entrepriseSelectionne = new ArrayList<Entreprise>();

	        System.out.println("Test !!!!!!!!!");
	        for (Entreprise unEntreprise : entreprises)
	        {
	        	
	            if (checked.get(unEntreprise.getId())) 
	            {
	            	 System.out.println(unEntreprise.getNom());
	            	
	            	//System.out.println("Selection : "+unEtudiant.getNom());
	            	
	            	 entrepriseEJB.removeEntreprise(unEntreprise);
	            	
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
		  return "listeEntreprise";
	  }


}
