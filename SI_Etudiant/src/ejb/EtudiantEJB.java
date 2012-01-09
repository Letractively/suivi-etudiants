package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Etudiant;

@Stateless 
public class EtudiantEJB 
{
	 @PersistenceContext(unitName = "si_etu")
	  private EntityManager em;
	 	
	 @SuppressWarnings("unchecked") 
	 public List<Etudiant> findAllEtudiants() 
	 {
		    List<Etudiant> results = em.createQuery("select e from Etudiant e").getResultList();
		    return results;
	 }
	 
	 public Etudiant createEtudiant(Etudiant etudiant) 
	 {
		 	
		em.persist(etudiant);
			
		return etudiant;
	 }
	 public void removeEtudiant(Etudiant etudiant) 
	 {
		 //em.merge(etudiant); =>entité doit être détaché du bean sinon cela ne fonctionne pas
		 em.remove(em.merge(etudiant));	
	 }
	 public void updateEtudiant(Etudiant etudiant) 
	 {
		 em.merge(etudiant);	
	 }
	 
}
