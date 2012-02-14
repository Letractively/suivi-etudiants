package bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import ejb.EtablissementEJB;
import ejb.EtudiantEJB;
import ejb.EtudiantFormationEJB;
import ejb.FormationEJB;
import entity.Etablissement;
import entity.Etudiant;
import entity.EtudiantFormation;
import entity.EtudiantFormationId;
import entity.Formation;

import util.DateUtil;
import util.EtablissementConfig;
import util.Redirection;

@ManagedBean(name = "etudiantFormationBean")
@ViewScoped
public class EtudiantFormationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EtudiantEJB etudiantEJB;

	@EJB
	private FormationEJB formationEJB;

	@EJB
	private EtudiantFormationEJB etudiantFormationEJB;

	@EJB
	private EtablissementEJB etablissementEJB;
	
	
	private Etudiant etudiant = new Etudiant();
	private EtudiantFormation etudiantFormation = new EtudiantFormation();
	private EtudiantFormationId etudiantFormationId = new EtudiantFormationId();
	private Formation formation = new Formation();
	private Etablissement etablissement = new Etablissement();

	private List<Etudiant> etudiants = new ArrayList<Etudiant>();
	private List<Formation> formations = new ArrayList<Formation>();
	private List<EtudiantFormation> etudiantFormations = new ArrayList<EtudiantFormation>();
	private List<Etablissement> etablissements = new ArrayList<Etablissement>();

	private HashMap<EtudiantFormationId, Boolean> checked = new HashMap<EtudiantFormationId, Boolean>();

	private List<SelectItem> etablissementsItems = new ArrayList<SelectItem>();
	// c'est cette variable qui aura l'id de l'établissement selectionné via la
	// liste déroulante
	private Long etablissementItemSelect;

	private List<SelectItem> formationsItems = new ArrayList<SelectItem>();
	// c'est cette variable qui aura l'id de la formation selectionné via la
	// liste déroulante
	private Long formationItemSelect;

	private EtudiantFormation selectedEtudiantFormation;

	private String formationTypeItemSelect;
	private List<SelectItem> formationsTypeItems = new ArrayList<SelectItem>();

	private Long id;

	@PostConstruct
	public void init() {
		/*
		 * On recupere l'id passe en parametre depuis l'autre page Attention :
		 * Il faut parser en type Long comme dans l'entite
		 */
		if (this.getPassedParameter() != null) {
			try {
				id = Long.parseLong(this.getPassedParameter());

				// Je remplis ma liste d'etudiantFormations grace a ma requete
				// etudiantEntreprises =
				// etudiantFormationEJB.findCompaniesByStudentId(id);
				etudiantFormations = etudiantFormationEJB
						.findAllFormationsByStudentId(id);

				// etablissements = etablissementEJB.findAllEtablissements();
				// formations =
				// formationEJB.findFormationsByEtablissementId(etablissementItemSelect);
				// Je recupere l'etudiant
				etudiant = etudiantEJB.findEtudiantById(id);

				// Je recupere l'etudiant

			} catch (NumberFormatException e) {
				Redirection.erreurXhtml();
			}

		}
		// etudiantFormations = etudiantFormationEJB.findAllEtudiantFormation();
		// Je remplis la liste d'entreprise pour la page d'ajout
		// etudiantEntreprise..
		etablissements = etablissementEJB.findAllEtablissements();
		formations = formationEJB.findAllFormations();

		// appel de la fonction qui initailise la liste d'item entreprise

		creerListeItem();
		creerListeTypeSelect();

	}

	public String ajout() {
		// pour recuper l'id de l'etudiant, j'ai mis un champ caché dans le
		// formulaire jsf pour faire le traitement ici, dans la fonction

		Long idEtudiant = etudiant.getId();
		System.out.println("el id del estudiante valeeeeeeeeeee" + idEtudiant);
		etudiantFormationId = new EtudiantFormationId(idEtudiant,
				formationItemSelect, etudiantFormationId.getDatedebut());

		this.etudiantFormation.setId(etudiantFormationId);
		this.etudiantFormation.setFormation(formationEJB
				.findFormationById(formationItemSelect));
		this.etudiantFormation.setEtudiant(etudiantEJB
				.findEtudiantById(idEtudiant));
		this.etudiantFormationEJB.createEtudiantFormation(etudiantFormation);
		Redirection.listeEtudiantEntreprise(idEtudiant);
		return "listeEtudiant";

	}

	public void supprimer() {

		Long idEtu = selectedEtudiantFormation.getEtudiant().getId();
		etudiantFormationEJB.removeEtudiantFormation(selectedEtudiantFormation);
		Redirection.listeEtudiantEntreprise(idEtu);

	}

	// procédure permettant de mettre à jour la liste de formation lorsque
	// établissement selectionné
	// j'aurais voulu manipuller directement un etablissement, mais dans la
	// combo ça marche pas si on passe comme id un etablissement au lieu d'un
	// entier
	public List<SelectItem> doSelectedEtab() {
		
		System.out.println("Entré dans la procédure");

		Long idEtab = etablissementItemSelect;

		System.out.println("idEtab select : " + idEtab);

		if (etablissementItemSelect != null
				&& !etablissementItemSelect.equals("")) {

			System.out.println("idEtab select : " + idEtab);

			// code utilisé pour l'instant, j'aurais voulais éviter cela, en
			// récupérant directement l'établissement au lieu du nombre
			
			/*
			 * Etablissement et;
			 *
			et = etablissementEJB
					.findEtablissementById(etablissementItemSelect);
			et.getLesFormations();
			*/
			
			// on supprime les éléments de la liste formation
			formationsItems.removeAll(formationsItems);

			formations= formationEJB.findFormationsByEtablissementId(idEtab);
			
			// conversion de la liste hashset en arrayliste formation
			//formations = new ArrayList<Formation>(et.getLesFormations());
			
			System.out.println("Taille formations : " + formations.size());

		}

		return creerListeItemFormation();
	}

	public List<EtudiantFormation> doSelectedTypeTableau() {
		System.out.println(id);

		if (formationTypeItemSelect.equals("APRES IUT")) {
			etudiantFormations = etudiantFormationEJB
					.findFormationsUlterieureByStudentId(id,
							EtablissementConfig.etablissement);
		}
		if (formationTypeItemSelect.equals("AVANT IUT")) {
			etudiantFormations = etudiantFormationEJB
					.findFormationsAnterieuresByStudentId(id,
							EtablissementConfig.etablissement);
		}
		if (formationTypeItemSelect.equals("IUT")) {
			etudiantFormations= etudiantFormationEJB
					.findFormationsByStudentId(id,
							EtablissementConfig.etablissement);
		}
		if (formationTypeItemSelect.equals("Tout")) {
			etudiantFormations = etudiantFormationEJB
					.findAllFormationsByStudentId(id);
		}
		this.setEtudiantFormations(etudiantFormations);
		
		return etudiantFormations;
		
	}
	
	public List<SelectItem> creerListeItem() {
		for (Etablissement eta : etablissements) {
			// identifiant,valeur
			etablissementsItems.add(new SelectItem(eta.getId(), eta.getNom()));

		}

		return etablissementsItems;
	}

	public List<SelectItem> creerListeItemFormation() {

		for (Formation forma : formations) {
			formationsItems.add(new SelectItem(forma.getId(), forma
					.getLibelle()));
		}

		return formationsItems;
	}

	public List<SelectItem> creerListeTypeSelect() {

		formationsTypeItems.add(new SelectItem("Tout", "Tout"));
		formationsTypeItems.add(new SelectItem("IUT", "IUT"));
		formationsTypeItems.add(new SelectItem("AVANT IUT", "AVANT IUT"));
		formationsTypeItems.add(new SelectItem("APRES IUT", "APRES IUT"));

		return formationsTypeItems;
	}
	

	public String getPassedParameter() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("id");
		return parametreId;
	}
	
	
	
	

	// getters and setters

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public EtudiantFormation getEtudiantFormation() {
		return etudiantFormation;
	}

	public void setEtudiantFormation(EtudiantFormation etudiantFormation) {
		this.etudiantFormation = etudiantFormation;
	}

	public EtudiantFormationId getEtudiantFormationId() {
		return etudiantFormationId;
	}

	public void setEtudiantFormationId(EtudiantFormationId etudiantFormationId) {
		this.etudiantFormationId = etudiantFormationId;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public List<Etablissement> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}

	public List<SelectItem> getEtablissementsItems() {
		return etablissementsItems;
	}

	public void setEtablissementsItems(List<SelectItem> etablissementsItems) {
		this.etablissementsItems = etablissementsItems;
	}

	public List<SelectItem> getFormationsItems() {
		return formationsItems;
	}

	public void setFormationsItems(List<SelectItem> formationsItems) {
		this.formationsItems = formationsItems;
	}

	public HashMap<EtudiantFormationId, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(HashMap<EtudiantFormationId, Boolean> checked) {
		this.checked = checked;
	}

	public List<EtudiantFormation> getEtudiantFormations() {
		return etudiantFormations;
	}

	public void setEtudiantFormations(List<EtudiantFormation> etudiantFormations) {
		this.etudiantFormations = etudiantFormations;
	}

	public Long getEtablissementItemSelect() {
		return etablissementItemSelect;
	}

	public void setEtablissementItemSelect(Long etablissementItemSelect) {
		this.etablissementItemSelect = etablissementItemSelect;
	}

	public Long getFormationItemSelect() {
		return formationItemSelect;
	}

	public void setFormationItemSelect(Long formationItemSelect) {
		this.formationItemSelect = formationItemSelect;
	}

	public EtudiantFormation getSelectedEtudiantFormation() {
		return selectedEtudiantFormation;
	}

	public void setSelectedEtudiantFormation(
			EtudiantFormation selectedEtudiantFormation) {
		this.selectedEtudiantFormation = selectedEtudiantFormation;
	}

	public String getFormationTypeItemSelect() {
		return formationTypeItemSelect;
	}

	public void setFormationTypeItemSelect(String formationTypeItemSelect) {
		this.formationTypeItemSelect = formationTypeItemSelect;
	}

	public List<SelectItem> getFormationsTypeItems() {
		return formationsTypeItems;
	}

	public void setFormationsTypeItems(List<SelectItem> formationsTypeItems) {
		this.formationsTypeItems = formationsTypeItems;
	}

}