package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Entreprise;
import entity.EtudiantEntreprise;

@Stateless 
public class EntrepriseEJB {
	
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;
	
	/*
	 * Retourne toutes les entreprises
	 */
	@SuppressWarnings("unchecked") 
	public List<Entreprise> findAllEntreprises() {
		List<Entreprise> results = em.createQuery("select e from Entreprise e").getResultList();
	    return results;		
	}

	/*
	 * Cree une entreprise
	 */
	public Entreprise createEntreprise(Entreprise entreprise) {
		em.persist(entreprise);
		return entreprise;
	}
	
	/*
	 * Supprime une entreprise
	 */
	public void removeEntreprise(Entreprise entreprise) {
		
		 em.remove(em.merge(entreprise));	
	}
	
	/*
	 * Met a jour une entreprise
	 */
	 public void updateEntreprise(Entreprise entreprise) {
		 em.merge(entreprise);	
	 }
	 
	 /*
	  * Cherche et retourne une entreprise par son identifiant
	  */
	 public Entreprise findEntrepriseById(Long id) {
		 Entreprise results = em.find(Entreprise.class, id);
	    return results;
	 }
	 
	 
}