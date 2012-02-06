package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.implement.EtudiantFormationEJBInterface;
import entity.EtudiantFormation;
@LocalBean
@Stateless
public class EtudiantFormationEJB implements EtudiantFormationEJBInterface
{
	@PersistenceContext(unitName = "si_etu")
	  private EntityManager em;
	 
	 /*
	  * Recupere tous les etudiants
	  
	*/
	@SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findAllEtudiantFormation() {
		 List<EtudiantFormation> results = em.createQuery(
				 "select e from EtudiantFormation e").getResultList();
		 return results;
	 }
	 /* 
	  * Retourne l'ensemble des entreprises rattachees a un etudiant (identifiant)
	  */
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findFormationsByStudentId(Long idEtudiant) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etf from EtudiantFormation etf where etf.id.etudiantId =:id").setParameter("id", idEtudiant).getResultList();
		 return results;
	 }
	 
	 /*
	  * Cree un etudiantEntreprise dans la BDD
	  */
	 public EtudiantFormation createEtudiantFormation(EtudiantFormation etudiantFormation) {
		 	
		em.persist(etudiantFormation);
			
		return etudiantFormation;
	 }
	 public void removeEtudiantFormation(EtudiantFormation etudiantFormation) {
			
		 em.remove(em.merge(etudiantFormation));	
	 }

}

