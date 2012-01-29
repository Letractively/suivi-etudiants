package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Formation;

@Stateless
public class FormationEJB {
	
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;
	
	/*
	 * Retourne toutes les formations
	 */
	@SuppressWarnings("unchecked") 
	public List<Formation> findAllFormations() {
		List<Formation> results = em.createQuery("select e from Formation e").getResultList();
	    return results;		
	}
	

	/*
	 * Cree une formation
	 */
	public Formation createFormation(Formation formation) {
		
		em.persist(formation);
		em.flush();
		
		
		return formation;
	}
	
	/*
	 * Supprime une formation
	 */
	public void removeFormation(Formation formation) {
		 
		 em.remove(em.merge(formation)); 
		
	}
	
	/*
	 * Met a jour une formation
	 */
	 public void updateFormation(Formation formation) {
		 em.merge(formation);	
		 
	 }
	 
	 /*
	  * Cherche et retourne une formation par son identifiant
	  */
	 public Formation findFormationById(Long id) {
		 Formation results = em.find(Formation.class, id);
	    return results;
	 }


	@SuppressWarnings("unchecked")
	public List<Formation> findFormationsByEtablissementId(Long id) {

		List<Formation> results = em.createQuery("select forma from Formation forma where forma.etablissement.id=:id").setParameter("id", id).getResultList();
		return results;
	}
	

}
