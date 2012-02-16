package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.implement.EtudiantEntrepriseEJBInterface;
import entity.EtudiantEntreprise;
import entity.EtudiantFormation;

@LocalBean
@Stateless
public class EtudiantEntrepriseEJB implements EtudiantEntrepriseEJBInterface{
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
	 * Retourne l'ensemble des entreprises rattachees a un etudiant
	 * (identifiant)
	 */
	@SuppressWarnings("unchecked")
	public List<EtudiantEntreprise> findCompaniesByStudentId(Long idEtudiant) {
		List<EtudiantEntreprise> results = em
				.createQuery(
						"select etuEnt from EtudiantEntreprise etuEnt where etuEnt.id.etudiantId =:id")
				.setParameter("id", idEtudiant).getResultList();
		return results;
	}
	
	@SuppressWarnings("unchecked") 
	 public List<EtudiantEntreprise> findFormationsByEntreprise(Long idEnt,Long idEtu) {
		 List<EtudiantEntreprise> results = em.createQuery(
				 "select etuEnt from EtudiantEntreprise etuEnt " +
				"where etuEnt.id.etudiantId = :etu  " +
				"and etuEnt.entreprise.id = :ent ").setParameter("etu", idEtu).setParameter("ent", idEnt).getResultList();
		 return results;
	 }
	/*
	 * Cree un etudiantEntreprise dans la BDD
	 */
	public EtudiantEntreprise createEtudiantEntreprise(
			EtudiantEntreprise etudiantEntreprise) {

		em.persist(etudiantEntreprise);

		return etudiantEntreprise;
	}

	public void removeEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise) {

		em.remove(em.merge(etudiantEntreprise));
	}

}
