package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.EtudiantEntreprise;

@Stateless
public class EtudiantEntrepriseEJB 
{
	@PersistenceContext(unitName = "si_etu")
	  private EntityManager em;
	 
	 /*
	  * Recupere tous les etudiants
	  
	*/
	@SuppressWarnings("unchecked") 
	 public List<EtudiantEntreprise> findAllEtudiantEntreprise() {
		 List<EtudiantEntreprise> results = em.createQuery(
				 "select e from EtudiantEntreprise e").getResultList();
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
	 
	 /*
	  * Cree un etudiantEntreprise dans la BDD
	  */
	 public EtudiantEntreprise createEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise) {
		 	
		em.persist(etudiantEntreprise);
			
		return etudiantEntreprise;
	 }
	 public void removeEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise) {
			
		 em.remove(em.merge(etudiantEntreprise));	
	 }
	
}
