package ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Etudiant;

@Stateless 
public class EtudiantEJB 
{
	 @PersistenceContext(unitName = "si_etu")
	  private EntityManager em;
	 
	 /*
	  * Recupere tous les etudiants
	  */
	 @SuppressWarnings("unchecked") 
	 public List<Etudiant> findAllEtudiants() throws EJBException {
		    List<Etudiant> results = em.createQuery("select e from Etudiant e").getResultList();
		    return results;
	 }
	 
	 /*
	  * Cree un etudiant dans la BDD
	  */
	 public Etudiant createEtudiant(Etudiant etudiant)throws EJBException {
		 	
		em.persist(etudiant);
			
		return etudiant;
	 }
	 
	 /*
	  * Supprime un etudiant
	  */
	 public void removeEtudiant(Etudiant etudiant)throws EJBException {
		 //em.merge(etudiant) => entité doit être détaché du bean sinon cela ne fonctionne pas
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
	    Etudiant results = (Etudiant) em.find(Etudiant.class,id);
	    return results;
	 }
}