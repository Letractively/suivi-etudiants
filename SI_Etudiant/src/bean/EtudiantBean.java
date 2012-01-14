package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ejb.EtudiantEJB;
import entity.Etudiant;


@ManagedBean(name = "etudiantBean")
@SessionScoped
public class EtudiantBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
  
	private Etudiant etudiant =new Etudiant();;
	private List<Etudiant> etudiants = new ArrayList<Etudiant>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private Etudiant editEtudiant;//on instancie pas, c'est l'etudiant que l'on récupere à partir du jsf 
	
	@PostConstruct
	public void init() 
	{
	    etudiants = etudiantEJB.findAllEtudiants();
	}  
	public String doNewEtudiant() 
	{
		return "newEtudiant.xhtml"; 
	}
	
	  public Etudiant getEtudiant() 
	  {
		  return etudiant;
	  }
	
	  public void setEtudiant(Etudiant etudiant) 
	  {
		  this.etudiant = etudiant;
	  }
	
	  public List<Etudiant> getEtudiants() 
	  {
		  return etudiants;
	  }
	
	  public void setEtudiants(List<Etudiant> etudiants) 
	  {
		  this.etudiants = etudiants;
	  }
	  
	  
	  public HashMap<Long, Boolean> getChecked() 
	  {
		return checked;
	  }
	  public void setChecked(HashMap<Long, Boolean> checked) 
	  {
		this.checked = checked;
	  }
	  
	  
	  public Etudiant getEditEtudiant() {
		return editEtudiant;
	}
	public void setEditEtudiant(Etudiant editEtudiant) {
		this.editEtudiant = editEtudiant;
	}
	public String ajout() 
	{
		  this.etudiantEJB.createEtudiant(etudiant);  
		  
		  //si on est en session, sinon pas besoin...
		  etudiants = etudiantEJB.findAllEtudiants();
		  etudiant =new Etudiant();
		  
		  return "list";
	  }
	  public void supprimer() 
	  {
		  	System.out.println("Test !!!!!!!!!");
	        System.out.println("Test !!!!!!!!!");
	        
	        for (Etudiant unEtudiant : etudiants)
	        {
	        	
	            if (checked.get(unEtudiant.getId())) 
	            {
	            	 System.out.println(unEtudiant.getNom());
	            	
	            	//System.out.println("Selection : "+unEtudiant.getNom());
	            	
	            	etudiantEJB.removeEtudiant(unEtudiant);
	            	
	            }
	        }
	        etudiants=etudiantEJB.findAllEtudiants();
	        
	   }
	  public String edit()
	  {
		  System.out.println(editEtudiant.getId());	
		  return "edit";
			
	  }
	  public String modifier() 
	  {
		  etudiantEJB.updateEtudiant(editEtudiant);
		  return "list";
	  }
	  
}
