package junit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;


import org.junit.BeforeClass;
import org.junit.Test;


import ejb.EtudiantEJB;
import ejb.UtilisateurEJB;
import ejb.implement.EtudiantEJBInterface;
import ejb.implement.UtilisateurEJBInterface;
import entity.Adresse;
import entity.Contact;
import entity.Etudiant;
import entity.Utilisateur;

public class UtilisateurTest {
	
	private static EJBContainer container;
    public static Context ctx;
    private static UtilisateurEJBInterface utiEJB;
	
   
    
    @BeforeClass
    public static void setup() throws NamingException {
      
        //créer une nouvelle instance du conteneur et de l'initialiser
        container =EJBContainer.createEJBContainer();
        
        //renvoyer un objet de type Context qui permet un accès à l'annuaire pour rechercher des ressources de type EJB Session 
        ctx = container.getContext();
        
        utiEJB=(UtilisateurEJBInterface) ctx.lookup("UtilisateurEJB");
       
    }
 	 
	@Test
	public void test() throws NamingException
	{
		
		Utilisateur u = new Utilisateur();
		
		u.setLogin("TestTest");
		u.setMail("test@yahoo.fr");
		u.setMotDePasse("212121");
		u.setNiveau("3");
		u.setNom("Test2");
		u.setPrenom("TestTest");
		
		int taille=utiEJB.findAllUtilisateurs().size();
		
		utiEJB.createUtilisateur(u);
	
		assertEquals(taille+1, utiEJB.findAllUtilisateurs().size());
		
		
	}

}
