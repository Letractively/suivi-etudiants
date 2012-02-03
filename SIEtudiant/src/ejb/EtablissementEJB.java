package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejbInterface.EtablissementEJBInterface;
import entity.Etablissement;
@LocalBean
@Stateless
public class EtablissementEJB implements  EtablissementEJBInterface{

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
