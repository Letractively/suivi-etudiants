package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import util.Redirection;

import ejb.UtilisateurEJB;
import entity.Etudiant;
import entity.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@RequestScoped
public class UtilisateurBean {
	
	@EJB
    private UtilisateurEJB utilisateurEJB;
	
	private HashMap<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private Utilisateur utilisateur = new Utilisateur();
    
    private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    
    @PostConstruct
    public void init() 
    {
            utilisateurs = utilisateurEJB.findAllUtilisateurs();
    }
    
    public void supprimer() 
	{
		for (Utilisateur unUtilisateur : utilisateurs)
        {
			if (checked.get(unUtilisateur.getId())) 
            {
            	utilisateurEJB.removeUtilisateur(unUtilisateur);	
            }
        }
	}
    
    //getters and setters
    public List<Utilisateur> getUtilisateurs() {
            return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
            this.utilisateurs = utilisateurs;
    }

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	} 
    

}
