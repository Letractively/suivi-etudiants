package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import ejb.UtilisateurEJB;
import entity.Utilisateur;

@ManagedBean(name = "utilisateurBean")
@RequestScoped
public class UtilisateurBean {
	
	@EJB
    private UtilisateurEJB utilisateurEJB;
    
    private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    
    @PostConstruct
    public void init() 
    {
            utilisateurs = utilisateurEJB.findAllUtilisateurs();
    }

    public List<Utilisateur> getUtilisateurs() {
            return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
            this.utilisateurs = utilisateurs;
    }  

}
