package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import entity.Entreprise;

@Stateless
public class SIEtudiantEAO 
{
	@PersistenceContext(unitName = "si_etu")
	private EntityManager em;
	
	
	public <T extends Entity> T findById(Class<T> clazz, Long id)
    throws EntityNotFoundException
    {
        T e = em.find(clazz, id);
        if (e == null) {
            throw new EntityNotFoundException();
        }
        return e;
    }
	
	public <T extends Entity> void persist(T entity) {
        
            em.persist(entity);
            
        }
}
 
	
