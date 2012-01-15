package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;

import ejb.EtudiantEJB;
import entity.Etudiant;


@ManagedBean(name = "etudiantBean")
@SessionScoped
public class EtudiantBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
  
	private Etudiant etudiant =new Etudiant();
	 
	private List<Etudiant> etudiants = new ArrayList<Etudiant>();
	
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	/*on instancie pas, c'est l'etudiant que l'on récupere à partir du jsf, il servira pour recupérer 
	 * l'étudiant que l'on souhaite modifier (voir jsf modifEtudiant)
	 */
	private Etudiant editEtudiant;
	
	@PostConstruct
	public void init() 
	{
	    etudiants = etudiantEJB.findAllEtudiants();
	}
	
	public String ajout() 
	{
		  this.etudiantEJB.createEtudiant(etudiant);  
		  
		  //si on est en session, actualisation de la liste d'étudiant et de l'étudiant
		  etudiants = etudiantEJB.findAllEtudiants();
		  etudiant =new Etudiant();
		  
		  return "list";
	}
	  
	//procédure pemettant de supprimer les etudiants selectionnés de la liste checked
	public void supprimer() 
	{
		for (Etudiant unEtudiant : etudiants)
        {
			if (checked.get(unEtudiant.getId())) 
            {
            	etudiantEJB.removeEtudiant(unEtudiant);	
            }
        }
		
		//si on est en session, actualisation de la liste d'étudiant
		etudiants=etudiantEJB.findAllEtudiants();
	}
	    
	//fonction permettant de modifier un etudiant => retourne list (voir face-config.xml) 
	public String modifier() 
	{
		etudiantEJB.updateEtudiant(editEtudiant);
		return "list";
	}
	
	//fonction retournant edit (voir face-config.xml)
	public String edit()
	{	
		  return "edit";	
	}
		
	  

	  //getter and setter  

	  
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
	  public Etudiant getEditEtudiant() 
	  {
		return editEtudiant;
	  }
	  public void setEditEtudiant(Etudiant editEtudiant) 
	  {
		this.editEtudiant = editEtudiant;
	  }
	  
}
