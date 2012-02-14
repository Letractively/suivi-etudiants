package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.implement.EtudiantEJBInterface;
import entity.Etudiant;

@LocalBean
@Stateless
public class EtudiantEJB implements EtudiantEJBInterface{
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;

	/*
	 * Recupere tous les etudiants
	 */
	@SuppressWarnings("unchecked")
	public List<Etudiant> findAllEtudiants() throws EJBException {
		List<Etudiant> results = em.createQuery("select e from Etudiant e")
				.getResultList();
		return results;
	}

	/*
	 * Cree un etudiant dans la BDD
	 */
	public Etudiant createEtudiant(Etudiant etudiant) throws EJBException {
		em.persist(etudiant);
		return etudiant;
	}

	/*
	 * Supprime un etudiant
	 */
	public void removeEtudiant(Etudiant etudiant) throws EJBException {
		// em.merge(etudiant) => entité doit être détaché du bean sinon cela ne
		// fonctionne pas
		em.remove(em.merge(etudiant));
	}

	/*
	 * Met a jour un etudiant
	 */
	public void updateEtudiant(Etudiant etudiant) {
		em.merge(etudiant);
	}

	/*
	 * Cherche et retourne un etudiant par son identifiant
	 */
	public Etudiant findEtudiantById(Long id) {
		Etudiant results = (Etudiant) em.find(Etudiant.class, id);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Etudiant> findAllEtudiantsByEnt(Long ent) throws EJBException {
		List<Etudiant> results = em
				.createQuery(
						"select DISTINCT e from Etudiant e, EtudiantEntreprise ent" +
						" where e.id=ent.id.etudiantId and ent.id.entrepriseId=:ent")
				.setParameter("ent", ent).getResultList();
		return results;
	}
	@SuppressWarnings("unchecked")
	public List<Etudiant> findAllEtudiantsByFormation(Long formation) throws EJBException {
		List<Etudiant> results = em
				.createQuery(
						"select DISTINCT e from Etudiant e, EtudiantFormation forma" +
						" where e.id=forma.id.etudiantId and forma.id.formationId=:formation")
				.setParameter("formation", formation).getResultList();
		return results;
	}
	
	
	public List<Etudiant> findAllEtudiantsActuel(Date dateActu,long idEtab) throws EJBException {
		@SuppressWarnings("unchecked")
		List<Etudiant> results = em
				.createQuery(
						"select DISTINCT e from Etudiant e, EtudiantFormation forma" +
						" where e.id=forma.id.etudiantId " +
						"and forma.formation.etablissement.id=:idEtab "+
						"and :dateActu between forma.id.datedebut and forma.datefin").setParameter("dateActu", dateActu)
						.setParameter("idEtab", idEtab).getResultList();
		return results;
	}
	public List<Etudiant> findAllEtudiantsAncien(Date dateActu,long idEtab) throws EJBException {
		@SuppressWarnings("unchecked")
		List<Etudiant> results = em
				.createQuery(
						"select DISTINCT e from Etudiant e, EtudiantFormation forma" +
						" where e.id=forma.id.etudiantId " +
						"and forma.formation.etablissement.id=:idEtab "+
						"and :dateActu > forma.datefin").setParameter("dateActu", dateActu)
						.setParameter("idEtab", idEtab).getResultList();
		return results;
	}
}