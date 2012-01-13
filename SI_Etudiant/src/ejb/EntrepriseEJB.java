package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Entreprise;

@Stateless 
public class EntrepriseEJB {
	
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;
	
	@SuppressWarnings("unchecked") 
	public List<Entreprise> findAllEntreprises() {
		List<Entreprise> results = em.createQuery("select e from Entreprise e").getResultList();
	    return results;		
	}

	public Entreprise createEntreprise(Entreprise entreprise) {
		
		em.persist(entreprise);
		return entreprise;
		
	}
	
	public void removeEntreprise(Entreprise entreprise) 
	 {
		 //em.merge(entreprise); =>entit� doit �tre d�tach� du bean sinon cela ne fonctionne pas
		 em.remove(em.merge(entreprise));	
	 }
	 public void updateEntreprise(Entreprise entreprise) 
	 {
		 em.merge(entreprise);	
	 }
	 public Entreprise findEntrepriseById(Long id) 
	 {
		 Entreprise results = ( Entreprise) em.createQuery("select e from Entreprise e where e.identreprise = :id").setParameter("id", id).getSingleResult();
	    return results;
	 }

}