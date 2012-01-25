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
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.Redirection;

import ejb.EtablissementEJB;
import entity.Etablissement;
import entity.Etudiant;

//named : propre � conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "etablissementBean")  
@ConversationScoped 
@ViewScoped
public class EtablissementBean implements Serializable
{
	
private static final long serialVersionUID = 1L;
	
	/* 
	 * 
	 * J'ai du ajouter le fichier beans.xml pour que la conversation fonctionne voir :
	 *  
	 * http://miageprojet2.unice.fr/Intranet_de_Michel_Buffa/Cours_composants_distribu%C3%A9s
	 * _pour_l'entreprise_%2F%2F_EJB_2009/FAQ_JSF2_%3A_solutions_aux_erreurs_les_plus_courantes
	 *
	 * d'ailleur l'absence de implements serializable fait buggu� le projet, impossible de le lancer 
	 *
	 * l�annotation @Inject permet l�injection et se base sur le type et non sur des expressions EL comme
	 * l�annotation @ManagedProperty utilis�e avec l�annotation @ManagedBean.
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
	private EtablissementEJB etablissementEJB;
  
	private Etablissement etablissement =new Etablissement();
	
	private List<Etablissement> etablissements = new ArrayList<Etablissement>();
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Etablissement editEtablissement;//on instancie pas, c'est l'etablissement que l'on r�cupere � partir du jsf 

	private Etablissement selectedEtablissement;



	@PostConstruct
	public void init() 
	{
		if (conversation.isTransient()) {
		conversation.begin();
		}
		try {
			etablissements=etablissementEJB.findAllEtablissements();
		} catch (EJBException e) {

			Redirection.erreurXhtml();
		}
	}
	
	public String ajout() 
	{
		this.etablissementEJB.createEtablissement(etablissement);  
		
		 conversation.end(); 
		
		/*si on est en session, actualisation du la liste d'entreprises et de entreprise
		entreprises = entrepriseEJB.findAllEntreprises();
		entreprise =new Entreprise();*/
		  
		return "listeEtablissement";
	}
	
	public void supprimer() 
	{
		for (Etablissement uneEtablissement : etablissements)
		{
			if (checked.get(uneEtablissement.getId()))
			{
				etablissementEJB.removeEtablissement(uneEtablissement);
			}
		}
		//si on est en session, actualisation de la liste d'entreprises
		etablissements=etablissementEJB.findAllEtablissements();
	}
	
	public String modifier() 
	{
		etablissementEJB.updateEtablissement(editEtablissement);
		
		conversation.end();  
		
		/*si on est en session, actualisation de la liste d'entreprise
		entreprises=entrepriseEJB.findAllEntreprises();*/
		  
		return "listeEtablissement";
	}
	  
	public String edit()
	{
		return "editEtablissement";
	}


	public Etablissement getEtablissement() {
		return etablissement;
	}


	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}


	public List<Etablissement> getEtablissements() {
		return etablissements;
	}


	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}


	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}


	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}	
	
	public Etablissement getEditEtablissement() {
		return editEtablissement;
	}	  
	public void setEditEtablissement(Etablissement editEtablissement) {
		this.editEtablissement = editEtablissement;
	}

	public Etablissement getSelectedEtablissement() {
		return selectedEtablissement;
	}

	public void setSelectedEtablissement(Etablissement selectedEtablissement) {
		this.selectedEtablissement = selectedEtablissement;
	}
}
