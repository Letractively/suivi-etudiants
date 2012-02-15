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
	@SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findFormationsByEtablissement(Long idEtab,Long idEtu) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etuForma from EtudiantFormation etuForma " +
				"where etuForma.id.etudiantId = :etu  " +
				"and etuForma.formation.etablissement.id = :etab ").setParameter("etu", idEtu).setParameter("etab", idEtab).getResultList();
		 return results;
	 }
	 /* 
	  * Retourne l'ensemble des entreprises rattachees a un etudiant (identifiant)
	  */
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findFormationsByStudentId(Long idEtudiant,long etab) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etf from EtudiantFormation etf where etf.id.etudiantId =:id " +
				 "and  etf.formation.etablissement.id = :etab ").setParameter("id", idEtudiant).setParameter("etab", etab).getResultList();
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

	 
	 
	 
	 
	 
	 
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findFormationsUlterieureByStudentId(Long idEtudiant, long etab) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etf from EtudiantFormation etf " +
				 "where etf.id.etudiantId =:id " +
				 "and  etf.formation.etablissement.id != :etab "+
				"and etf.id.datedebut > (select max(etf2.datefin) from EtudiantFormation etf2 " +
				"where etf2.formation.etablissement.id = :etab)").
				setParameter("id", idEtudiant)
				.setParameter("etab", etab).	
				getResultList();
		 return results;
	 }
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findFormationsAnterieuresByStudentId(Long idEtudiant, long etab) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etf from EtudiantFormation etf " +
				 "where etf.id.etudiantId =:id " +
				 "and  etf.formation.etablissement.id != :etab "+
				"and etf.datefin < (select min(etf2.id.datedebut) from EtudiantFormation etf2 " +
				"where etf2.formation.etablissement.id = :etab)").
				setParameter("id", idEtudiant)
				.setParameter("etab", etab).	
				getResultList();
		 return results;
	 }
	 
	 @SuppressWarnings("unchecked") 
	 public List<EtudiantFormation> findAllFormationsByStudentId(Long idEtudiant) {
		 List<EtudiantFormation> results = em.createQuery(
				 "select etf from EtudiantFormation etf where etf.id.etudiantId =:id ").setParameter("id", idEtudiant).getResultList();
		 return results;
	 }
	 
	 
}

