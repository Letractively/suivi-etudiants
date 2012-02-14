package bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import util.DateUtil;
import util.EtablissementConfig;
import util.GenereDocument;
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

	/*
	 * on instancie pas, c'est l'etudiant que l'on récupere à partir du jsf, il
	 * servira pour recupérer l'étudiant que l'on souhaite modifier (voir jsf
	 * modifEtudiant)
	 */
	private Etudiant editEtudiant;

	private Etudiant selectedEtudiant;

	private String etudiantTypeItemSelect;
	private List<SelectItem> etudiantsTypeItems = new ArrayList<SelectItem>();

	@PostConstruct
	public void init() {
		if (conversation.isTransient()) {
			conversation.begin();

			try {
				// si l'on récupére ent dans l'url, alors la liste d'étudiant
				// affichée est la liste d'étudiant de lentreprise
				if (this.getPassedParameter("ent")!= null) 
				{
					Long ent = Long.parseLong(this.getPassedParameter("ent"));
					etudiants = etudiantEJB.findAllEtudiantsByEnt(ent);
					msg=" ayant travaillé à "+entrepriseEJB.findEntrepriseById(ent).getNom();
					
				}
				else
				{
					if(this.getPassedParameter("forma")!=null)
					{		
						System.out.println("salut");
						Long forma = Long.parseLong(this.getPassedParameter("forma"));
						etudiants=etudiantEJB.findAllEtudiantsByFormation(forma);
						
						Formation form= formationEJB.findFormationById(forma);
						msg=" ayant effectué la formation "+form.getLibelle()+" à "+form.getEtablissement().getNom();
						
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

		creerListeTypeSelect();
	}

	public String ajout() {
		// Ajout
		this.etudiantEJB.createEtudiant(etudiant);
		// Fin
		conversation.end();

		/*
		 * si on est en session, actualisation de la liste d'étudiant et de
		 * l'étudiant etudiants = etudiantEJB.findAllEtudiants(); etudiant =new
		 * Etudiant();
		 */
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
		 * etudiants=etudiantEJB.findAllEtudiants();
		 */

		Redirection.listeEtudiants();
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
		GenereDocument.creerListeEtudiantsPDF(etudiants, "liste_etudiants");
	}

	public void creerListeEtudiantsXLS() {
		GenereDocument.creerListeEtudiantsXLS(etudiants, "liste_etudiants");
	}

	public String getPassedParameter(String param) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get(param);
		return parametreId;
	}

	public List<SelectItem> creerListeTypeSelect() {

		etudiantsTypeItems.add(new SelectItem("Tout", "Tout (Inscrit dans la BD)"));
		etudiantsTypeItems.add(new SelectItem("Actuel",
				"Etudiants actuellement à l'IUT"));
		etudiantsTypeItems.add(new SelectItem("Ancien",
				"Ancien étudiants de l'IUT"));

		return etudiantsTypeItems;
	}

	public void doSelectedTypeEtu() throws ParseException {
		System.out.println(etudiantTypeItemSelect);

		try {
			System.out.println(DateUtil.date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date d = DateUtil.date();
		
		if (etudiantTypeItemSelect.equals("Actuel")) {
			etudiants = etudiantEJB.findAllEtudiantsActuel(d,
					EtablissementConfig.etablissement);
		}
		if (etudiantTypeItemSelect.equals("Tout")) {
			etudiants = etudiantEJB.findAllEtudiants();
		}
		if (etudiantTypeItemSelect.equals("Ancien")) {
			etudiants = etudiantEJB.findAllEtudiantsAncien(d,
					EtablissementConfig.etablissement);
		}

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

	public String getEtudiantTypeItemSelect() {
		return etudiantTypeItemSelect;
	}

	public void setEtudiantTypeItemSelect(String etudiantTypeItemSelect) {
		this.etudiantTypeItemSelect = etudiantTypeItemSelect;
	}

	public List<SelectItem> getEtudiantsTypeItems() {
		return etudiantsTypeItems;
	}

	public void setEtudiantsTypeItems(List<SelectItem> etudiantsTypeItems) {
		this.etudiantsTypeItems = etudiantsTypeItems;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
