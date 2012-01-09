package bean;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import ejb.UtilisateurEJB;
import entity.Utilisateur;
import util.MD5Password;

@SessionScoped
@ManagedBean
public class Login {

	private Utilisateur utilisateur;

	private String username;
	private String password;
	
	@EJB
	private UtilisateurEJB utilisateurEJB;

    private Logger logger = Logger.getLogger(Login.class.getName());

	
	@NotNull
	@Length(min = 3, max = 25)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Length(min = 4, max = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() 
	{
		logger.log(Level.WARNING, "DEMANDE DE CONNEXION");
		FacesMessage msg = null;
		String dest = "site";
		List<Utilisateur> results = utilisateurEJB.findUtilisateurByLogin(username);
		logger.log(Level.WARNING, "taille de la liste : " + results.size());
		if (!results.isEmpty()) 
		{
			try 
			{
				if (MD5Password.testPassword(password, results
						.get(0).getMotdepasse()))
					utilisateur = results.get(0);
				else
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erreur Login", "Authentification invalide");
				dest = null;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue " + username,
					"connexion de " + username);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Login",
					"Authentification invalide");
            dest = null;
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return dest;
	}

	public String logout() 
	{
		utilisateur = null;
		FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);  
        session.invalidate();  
		return "logout";
	}

	public boolean isLoggedIn() {
		return utilisateur != null;
	}
	public boolean isAdmin() 
	{
		utilisateur.getNiveau();
		if(utilisateur.getNiveau()==0)
		{
			return true;
		}
		return false;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}