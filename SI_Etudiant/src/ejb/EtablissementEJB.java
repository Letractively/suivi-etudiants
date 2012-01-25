package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Etablissement;

@Stateless
public class EtablissementEJB {

	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;
	
	/*
	 * Retourne toutes les entreprises
	 */
	@SuppressWarnings("unchecked") 
	public List<Etablissement> findAllEtablissements() {
		List<Etablissement> results = em.createQuery("select e from Etablissement e").getResultList();
	    return results;		
	}
	

	/*
	 * Cree une entreprise
	 */
	public Etablissement createEtablissement(Etablissement etablissement) {
		em.persist(etablissement);
		return etablissement;
	}
	
	/*
	 * Supprime une entreprise
	 */
	public void removeEtablissement(Etablissement etablissement) {
		
		 em.remove(em.merge(etablissement));	
	}
	
	/*
	 * Met a jour une entreprise
	 */
	 public void updateEtablissement(Etablissement etablissement) {
		 em.merge(etablissement);	
	 }
	 
	 /*
	  * Cherche et retourne une entreprise par son identifiant
	  */
	 public Etablissement findEtablissementById(Long id) {
		 Etablissement results = em.find(Etablissement.class, id);
	    return results;
	 }
}
