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
		 //em.merge(entreprise); =>entit� doit �tre d�tach� du bean sinon cela ne fonctionne pas
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
		 Entreprise results = ( Entreprise) em.createQuery("select e from Entreprise e where e.identreprise = :id").setParameter("id", id).getSingleResult();
	    return results;
	 }
	 
	 /*
	  * Retourne l'ensemble des entreprises rattachees a un etudiant (identifiant)
	  */
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantEntreprise> findCompaniesByStudentId(Long idEtudiant) {
		 List<EtudiantEntreprise> results = em.createQuery(
				 "select etuEnt from EtudiantEntreprise etuEnt where etuEnt.id.etudiantId =:id").setParameter("id", idEtudiant).getResultList();
		 return results;
	 }
}