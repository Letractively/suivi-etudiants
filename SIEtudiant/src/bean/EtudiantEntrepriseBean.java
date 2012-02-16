package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import util.GenereDocument;
import util.Page;
import util.Redirection;

import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import ejb.EtudiantEntrepriseEJB;
import entity.Entreprise;
import entity.Etudiant;
import entity.EtudiantEntreprise;
import entity.EtudiantEntrepriseId;

@ManagedBean(name = "etudiantEntrepriseBean")
@ViewScoped
public class EtudiantEntrepriseBean {
	@EJB
	private EtudiantEJB etudiantEJB;

	@EJB
	private EntrepriseEJB entrepriseEJB;

	@EJB
	private EtudiantEntrepriseEJB etudiantEntrepriseEJB;

	private Etudiant etudiantEnt = new Etudiant();

	@Inject
	EtudiantFormationBean efb;

	private EtudiantEntreprise etudiantEntreprise = new EtudiantEntreprise();
	private EtudiantEntrepriseId etudiantEntrepriseId = new EtudiantEntrepriseId();

	private List<EtudiantEntreprise> etudiantEntreprises = new ArrayList<EtudiantEntreprise>();
	private List<Entreprise> entreprises = new ArrayList<Entreprise>();

	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	private List<SelectItem> contratsItems = new ArrayList<SelectItem>();

	// c'est cette variable qui aura l'id de lentreprise selectionné via la
	// liste déroulante
	private Long entrepriseItemSelected;
	private String contratItemSelect;

	private Long id;

	private EtudiantEntreprise selectedEtudiantEntreprise = new EtudiantEntreprise();

	@PostConstruct
	public void init() {
		/*
		 * On recupere l'id passe en parametre depuis l'autre page Attention :
		 * Il faut parser en type Long comme dans l'entite
		 */
		if (Page.pageCourante().equals(
				"/" + Redirection.domain + "/listeEtudiantEntreprise.faces")) {

			if (Page.getPassedParameter("id") != null) {
				try {
					id = Long.parseLong(Page.getPassedParameter("id"));
					// Je remplis ma liste d'etudiantEntreprises grace a ma
					// requete
					etudiantEntreprises = etudiantEntrepriseEJB
							.findCompaniesByStudentId(id);

					// Je recupere l'etudiant
					etudiantEnt = etudiantEJB.findEtudiantById(id);

					entreprises = entrepriseEJB.findEntreprisesByStudent(id);
					creerListeItemEntreprise();

				} catch (NumberFormatException e) {
					// Redirection.erreurXhtml();
				}
			}
		} else {
			try {
				id = Long.parseLong(Page.getPassedParameter("id"));

				// Je recupere l'etudiant
				etudiantEnt = etudiantEJB.findEtudiantById(id);

				entreprises = entrepriseEJB.findAllEntreprises();

				// appel de la fonction qui initailise la liste d'item
				// entreprise
				creerListeItemEntreprise();
				creerListeItemContrat();

			} catch (NumberFormatException e) {
				Redirection.erreurXhtml();
			}

		}

	}

	public void ajout() {
		// pour recuper l'id de l'etudiant, j'ai mis un champ caché dans le
		// formulaire jsf pour faire le traitement ici, dans la fonction

		Long idEtudiant = etudiantEnt.getId();

		// j'instancie ma clé primaire
		etudiantEntrepriseId = new EtudiantEntrepriseId(
				etudiantEntrepriseId.getDatedebut(), idEtudiant,
				entrepriseItemSelected);

		// mise en place de la clé primaire pour etudiatentreprise
		etudiantEntreprise.setId(etudiantEntrepriseId);

		// je donne le contrat selectionné à l'étudiant (cat il n'est pas
		// rataché directement dans le jsf)
		etudiantEntreprise.setTypecontrat(contratItemSelect);

		etudiantEntreprise.setEntreprise(entrepriseEJB
				.findEntrepriseById(entrepriseItemSelected));
		etudiantEntreprise
				.setEtudiant(etudiantEJB.findEtudiantById(idEtudiant));

		// On ajoute étudiantEntrepise dans la BDD
		etudiantEntrepriseEJB.createEtudiantEntreprise(etudiantEntreprise);

		// redirection vers la liste des activités. Seul solution trouvée pour
		// passé l'id en parametre
		Redirection.listeEtudiantEntreprise(idEtudiant);

	}

	public void supprimer() {

		// on récupére l'id de l'étudiant de cette manière avant de le supprimer
		Long idEtu = selectedEtudiantEntreprise.getEtudiant().getId();

		System.out.println("COUCOU");
		System.out.println(idEtu);

		etudiantEntrepriseEJB
				.removeEtudiantEntreprise(selectedEtudiantEntreprise);

		Redirection.listeEtudiantEntreprise(idEtu);

		/**
		 * for (EtudiantEntreprise unEtudiantEntreprise : etudiantEntreprises) {
		 * System.out.println("Test");
		 * 
		 * if (checked.get(unEtudiantEntreprise.getId())) {
		 * System.out.println("Test"); //
		 * etudiantEntrepriseEJB.removeEtudiantEntreprise(unEtudiantEntreprise);
		 * } }
		 */

	}

	/*
	 * Genere un PDF pour l'etudiant selectionne avec ses entreprises et ses
	 * formations
	 */
	public void creerEtudiantPDF() {

		System.out.println(efb.getEtudiantFormations().size());

		GenereDocument.creerEtudiantPDF(etudiantEnt, etudiantEntreprises,
				efb.getEtudiantFormations(), "etudiant_"
						+ etudiantEnt.getPrenom().toString() + "_"
						+ etudiantEnt.getNom().toString());
	}

	/*
	 * Genere un XLS pour un etudiant avec ses entreprises et ses formations
	 */
	public void creerEtudiantXLS() {
		GenereDocument.creerEtudiantXLS(etudiantEnt, etudiantEntreprises,
				efb.getEtudiantFormations(), "etudiant_"
						+ etudiantEnt.getPrenom().toString() + "_"
						+ etudiantEnt.getNom().toString());
	}

	// création de la liste d'item Entreprise, necessaire pour la page
	// ajouterEtudiantEntreprisexhtml
	public List<SelectItem> creerListeItemEntreprise() {
		for (Entreprise ent : entreprises) {
			// identifiant,valeur
			entreprisesItems.add(new SelectItem(ent.getId(), ent.getNom() + " "
					+ ent.getSiret()));
		}

		return entreprisesItems;
	}

	public List<SelectItem> creerListeItemContrat() {

		contratsItems.add(new SelectItem("CDI", "CDI"));
		contratsItems.add(new SelectItem("CDD", "CDD"));
		contratsItems.add(new SelectItem("Intérim", "Intérim"));
		contratsItems.add(new SelectItem("Stage", "Stage"));
		contratsItems.add(new SelectItem("Contrat de professionnalisation",
				"Contrat de professionnalisation"));
		contratsItems.add(new SelectItem("Contrat d'apprentissage",
				"Contrat d'apprentissage"));
		contratsItems.add(new SelectItem("Autre", "Autre"));

		return contratsItems;
	}

	public List<EtudiantEntreprise> doSelectedEntrepriseEtuEnt() {
		System.out.println("Entré dans la procédure");

		Long idEnt = this.entrepriseItemSelected;

		this.etudiantEntreprises.removeAll(etudiantEntreprises);
		this.setEtudiantEntreprises(etudiantEntrepriseEJB
				.findFormationsByEntreprise(idEnt, id));

		return etudiantEntreprises;
	}

	// getters and setters

	public EtudiantEntreprise getEtudiantEntreprise() {
		return etudiantEntreprise;
	}

	public void setEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise) {
		this.etudiantEntreprise = etudiantEntreprise;
	}

	public List<SelectItem> getEntreprisesItems() {
		return entreprisesItems;
	}

	public void setEntreprisesItems(List<SelectItem> entreprisesItems) {
		this.entreprisesItems = entreprisesItems;
	}

	public List<EtudiantEntreprise> getEtudiantEntreprises() {
		return etudiantEntreprises;
	}

	public void setEtudiantEntreprises(
			List<EtudiantEntreprise> etudiantEntreprises) {
		this.etudiantEntreprises = etudiantEntreprises;
	}

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Etudiant getEtudiantEnt() {
		return etudiantEnt;
	}

	public void setEtudiantEnt(Etudiant etudiant) {
		this.etudiantEnt = etudiant;
	}

	public Long getEntrepriseItemSelected() {
		return entrepriseItemSelected;
	}

	public void setEntrepriseItemSelected(Long entrepriseItemSelected) {
		this.entrepriseItemSelected = entrepriseItemSelected;
	}

	public EtudiantEntrepriseId getEtudiantEntrepriseId() {
		return etudiantEntrepriseId;
	}

	public void setEtudiantEntrepriseId(
			EtudiantEntrepriseId etudiantEntrepriseId) {
		this.etudiantEntrepriseId = etudiantEntrepriseId;
	}

	public EtudiantEntreprise getSelectedEtudiantEntreprise() {
		return selectedEtudiantEntreprise;
	}

	public void setSelectedEtudiantEntreprise(
			EtudiantEntreprise selectedEtudiantEntreprise) {
		this.selectedEtudiantEntreprise = selectedEtudiantEntreprise;
	}

	public String getContratItemSelect() {
		return contratItemSelect;
	}

	public void setContratItemSelect(String contratItemSelect) {
		this.contratItemSelect = contratItemSelect;
	}

	public List<SelectItem> getContratsItems() {
		return contratsItems;
	}

	public void setContratsItems(List<SelectItem> contratsItems) {
		this.contratsItems = contratsItems;
	}

}
