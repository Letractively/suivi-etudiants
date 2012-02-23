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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import util.GenereDocument;
import util.Page;
import util.Redirection;
import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import ejb.FormationEJB;
import entity.Etudiant;
import entity.Formation;

//named : propre à conversationScoped, ne surtout pas utiliser managedBean 
@Named(value = "etudiantBean")
@ConversationScoped
public class EtudiantBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	Conversation conversation;

	@EJB
	private EtudiantEJB etudiantEJB;
	
	@EJB
	private EntrepriseEJB entrepriseEJB;
	
	@EJB
	private FormationEJB formationEJB;

	private Etudiant etudiant = new Etudiant();

	private List<Etudiant> etudiants = new ArrayList<Etudiant>();

	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private Boolean dateFinBoolean;
	
	private String msg=null;
	
	private String titrePDF="";

	/*
	 * on instancie pas, c'est l'etudiant que l'on récupere à partir du jsf, il
	 * servira pour recupérer l'étudiant que l'on souhaite modifier (voir jsf
	 * modifEtudiant)
	 */
	private Etudiant editEtudiant;

	private Etudiant selectedEtudiant;

	@PostConstruct
	public void init() {
		//solution pour ne pas relancer le bean lors de l'appui sur le boutton pdf, excel.
		//la liste d'étdudiant est desormais correcte.
		/*if(conversation.isTransient())
		{	
			conversation.begin();*/
		
		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.begin();

			try {
				// si l'on récupére ent dans l'url, alors la liste d'étudiant
				// affichée est la liste d'étudiant de lentreprise
				if (Page.getPassedParameter("ent")!= null) 
				{
					Long ent = Long.parseLong(Page.getPassedParameter("ent"));
					etudiants = etudiantEJB.findAllEtudiantsByEnt(ent);
					msg=" ayant travaillé à "+entrepriseEJB.findEntrepriseById(ent).getNom();
					titrePDF=entrepriseEJB.findEntrepriseById(ent).getNom();
				}
				else
				{
					if(Page.getPassedParameter("forma")!=null)
					{		
						System.out.println("salut");
						Long forma = Long.parseLong(Page.getPassedParameter("forma"));
						etudiants=etudiantEJB.findAllEtudiantsByFormation(forma);
						
						Formation form= formationEJB.findFormationById(forma);
						msg=" ayant effectué la formation "+form.getLibelle()+" à "+form.getEtablissement().getNom();
						
						titrePDF=form.getEtablissement().getNom();
					}
					else
					{
						etudiants = etudiantEJB.findAllEtudiants();
					
					}
				} 
			} catch (EJBException e) {

				Redirection.erreurXhtml();
			}
		}
		
	}

	public String ajout() {
		// Ajout
		this.etudiantEJB.createEtudiant(etudiant);
		// Fin
		conversation.end();
		
		Redirection.listeEtudiants();
		
		return "listEtudiant";
	}

	// procédure pemettant de supprimer les etudiants selectionnés de la liste
	// checked
	public void supprimer() {
		for (Etudiant unEtudiant : etudiants) {
			
			if (checked.get(unEtudiant.getId())) {
				
				etudiantEJB.removeEtudiant(unEtudiant);
			}
		}
		conversation.end();
		/*
		 * si on est en session, actualisation de la liste d'étudiant
		 
		 */

		Redirection.listeEtudiants();
		
		etudiants=etudiantEJB.findAllEtudiants();
	}

	// fonction permettant de modifier un etudiant => retourne list (voir
	// face-config.xml)
	public String modifier() {
		etudiantEJB.updateEtudiant(editEtudiant);
		conversation.end();
		return "listEtudiant";
	}

	// fonction retournant edit (voir face-config.xml)
	public String edit() {
		return "edit";
	}

	public void creerListeEtudiantsPDF() {
		// Appeler la procédure pour creer mon PDF d'etudiants
		System.out.println(titrePDF);
		GenereDocument.creerListeEtudiantsPDF(etudiants, "liste_etudiants_"+titrePDF);
	}

	public void creerListeEtudiantsXLS() {
		GenereDocument.creerListeEtudiantsXLS(etudiants, "liste_etudiants_"+titrePDF);
	}

	

	public int nbEtudiant() {
		return etudiants.size();

	}
	
	public int nbErreur()
	{
		return 0;
	}
	
	

	/*
	 * Getters & Setters
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public HashMap<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Etudiant getEditEtudiant() {
		return editEtudiant;
	}

	public void setEditEtudiant(Etudiant editEtudiant) {
		this.editEtudiant = editEtudiant;
	}

	public Etudiant getSelectedEtudiant() {
		return selectedEtudiant;
	}

	public void setSelectedEtudiant(Etudiant selectedEtudiant) {
		this.selectedEtudiant = selectedEtudiant;
	}

	public Boolean getDateFinBoolean() {
		return dateFinBoolean;
	}

	public void setDateFinBoolean(Boolean dateFinBoolean) {
		this.dateFinBoolean = dateFinBoolean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
