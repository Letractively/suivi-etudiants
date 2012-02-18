package ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.implement.UtilisateurEJBInterface;
import entity.Utilisateur;
@LocalBean
@Stateless
public class UtilisateurEJB implements UtilisateurEJBInterface{
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllUtilisateurs() {
		List<Utilisateur> results = em.createQuery(
				"select u from Utilisateur u").getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findUtilisateurByLogin(String login) {
		List<Utilisateur> results = em
				.createQuery(
						"select u from Utilisateur u where u.login = :login")
				.setParameter("login", login).getResultList();
		return results;
	}
	@SuppressWarnings("unchecked")
	public Utilisateur findUserByLogin(String login) {
		Utilisateur results = em.find(Utilisateur.class,login);
				
		return results;
	}
	public void updateUtilisateur(Utilisateur utilisateur) {
		em.merge(utilisateur);
	}

	public void removeUtilisateur(Utilisateur utilisateur) {
		em.remove(em.merge(utilisateur));
	}

	public Utilisateur createUtilisateur(Utilisateur utilisateur)
			throws EJBException {

		em.persist(utilisateur);

		return utilisateur;
	}
}
