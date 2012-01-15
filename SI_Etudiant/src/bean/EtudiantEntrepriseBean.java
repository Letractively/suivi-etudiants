package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ejb.EntrepriseEJB;
import ejb.EtudiantEJB;
import entity.Entreprise;
import entity.Etudiant;
import entity.EtudiantEntreprise;


@ManagedBean(name = "etudiantEntrepriseBean")
@RequestScoped
public class EtudiantEntrepriseBean 
{
	@EJB
	private EtudiantEJB etudiantEJB;
	
	@EJB
	private EntrepriseEJB entrepriseEJB;

	private EtudiantEntreprise etudiantEntreprise = new  EtudiantEntreprise();
	
	private List<EtudiantEntreprise> entreprises = new ArrayList<EtudiantEntreprise>();
	private List<SelectItem> entreprisesItems = new ArrayList<SelectItem>();
	
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();


	@PostConstruct
	public void init() 
	{  
		
		/*
		 *  On recupere l'id passe en parametre depuis l'autre page
		 *  Attention : Il faut parser en type Long comme dans l'entite
		 */
		Long id = Long.parseLong(this.getPassedParameter());
		
		if(id!=null) 
		{
			
			// Je remplis ma liste d'entreprises grace a ma requete
			entreprises = entrepriseEJB.findCompaniesByStudentId(id);
			
			//entreprises.get(0).getEntreprise().getNom();
			
		}
	}
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

	public EntrepriseEJB getEntrepriseEJB() {
		return entrepriseEJB;
	}

	public void setEntrepriseEJB(EntrepriseEJB entrepriseEJB) {
		this.entrepriseEJB = entrepriseEJB;
	}

	public List<EtudiantEntreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<EtudiantEntreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public String ajout() 
	{
		//phase de test avec des valeur par défaut (non récupérer à partir des jsf)
		
		//Définition d'un étudiant
		Etudiant etu = etudiantEJB.findEtudiantById(51L);
		System.out.println("Le nom de l'étudiant est "+etu.getNom());
		
		//Entreprise ent=entrepriseEJB.findEntrepriseById(1L);
		//System.out.println("Le nom de l'entreprise est "+ent.getNom());
		
		EtudiantEntreprise ac= new EtudiantEntreprise();
		ac.setEtudiant(etu);
		
		//System.out.println("ttttt "+ac.getId().getIdetudiant());

		return "list";
	}
	
	public String getPassedParameter() 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String parametreId = (String) facesContext.getExternalContext().
		getRequestParameterMap().get("id");
		return parametreId;
	}
}
	