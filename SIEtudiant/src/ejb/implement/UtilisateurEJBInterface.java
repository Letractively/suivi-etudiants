package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.Utilisateur;
@Remote
public interface UtilisateurEJBInterface {
	List<Utilisateur> findAllUtilisateurs();
	List<Utilisateur> findUtilisateurByLogin(String login);
	void updateUtilisateur(Utilisateur utilisateur);
	void removeUtilisateur(Utilisateur utilisateur);
	Utilisateur createUtilisateur(Utilisateur utilisateur);
	Utilisateur findUserByLogin(String login);
	
}
