package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.Redirection;


import ejb.EtudiantEJB;
import entity.Etudiant;

//named : propre à conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "etudiantBean")  
@ConversationScoped 
public class EtudiantBean implements Serializable
{
	private static final long serialVersionUID = 1L;


	/* 
	 * 
	 * J'ai du ajouter le fichier beans.xml pour que la conversation fonctionne voir :
	 *  
	 * http://miageprojet2.unice.fr/Intranet_de_Michel_Buffa/Cours_composants_distribu%C3%A9s
	 * _pour_l'entreprise_%2F%2F_EJB_2009/FAQ_JSF2_%3A_solutions_aux_erreurs_les_plus_courantes
	 *
	 * d'ailleur l'absence de implements serializable fait buggué le projet, impossible de le lancer 
	 *
	 * l’annotation @Inject permet l’injection et se base sur le type et non sur des expressions EL comme
	 * l’annotation @ManagedProperty utilisée avec l’annotation @ManagedBean.
	 * 
	 * Donc 
	 *
	 * Inject si on est en named
	 * 
	 * Managed property si on est en managedBean
	 * 
	 * Inject permet d'injecter un autre bean
	 * 
	 * exemple : @inject EntrepriseBean entrepriseBean
	
	*/
	@Inject  
	Conversation conversation;  
	
	
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
		conversation.begin();
	    try 
	    {
			etudiants = etudiantEJB.findAllEtudiants();
		} catch (EJBException e) 
		{
			
			Redirection.erreurXhtml();
		}
	      
	}
	public String ajout() 
	{
		  this.etudiantEJB.createEtudiant(etudiant);  
		  
		  conversation.end();  
		  
		  /*si on est en session, actualisation de la liste d'étudiant et de l'étudiant
		  etudiants = etudiantEJB.findAllEtudiants();
		  etudiant =new Etudiant();*/
		  
		  return "listEtudiant";
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
		conversation.end();  
		/*si on est en session, actualisation de la liste d'étudiant
		etudiants=etudiantEJB.findAllEtudiants();*/
	}
	    
	//fonction permettant de modifier un etudiant => retourne list (voir face-config.xml) 
	public String modifier() 
	{
		etudiantEJB.updateEtudiant(editEtudiant);
		conversation.end();  
		return "listEtudiant";
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
