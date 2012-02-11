package bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ejb.EntrepriseEJB;
import ejb.EtablissementEJB;
import ejb.EtudiantEJB;
import ejb.FormationEJB;


@ManagedBean(name = "recapBean")
@ViewScoped
public class RecapBean{
	
	
	@EJB FormationEJB formaEJB;
	@EJB EntrepriseEJB entEJB;
	@EJB EtablissementEJB etabEJB;
	@EJB EtudiantEJB etEJB;
	
	
	@PostConstruct
	public void init() {
		
	}
	
	
	public int nbFormation()
	{
		return formaEJB.findAllFormations().size();
	}
	public int nbEntreprise()
	{
		return entEJB.findAllEntreprises().size();
	}
	public int nbEtablissement()
	{
		return etabEJB.findAllEtablissements().size();
	}
	public int nbEtudiant()
	{
		return etEJB.findAllEtudiants().size();
	}
	
}


