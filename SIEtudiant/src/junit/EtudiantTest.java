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
import entity.Adresse;
import entity.Contact;
import entity.Etudiant;
import entity.Utilisateur;

public class EtudiantTest {
	private static javax.ejb.embeddable.EJBContainer container;
    public static Context ctx;
    private static EtudiantEJBInterface etuEJB;
	
    @EJB
    private UtilisateurEJB uEJB; 
    
    @BeforeClass
    public static void setup() throws NamingException {
            	
        //créer une nouvelle instance du conteneur et de l'initialiser
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        
        //renvoyer un objet de type Context qui permet un accès à l'annuaire pour rechercher des ressources de type EJB Session
        ctx = container.getContext();
        
       etuEJB=(EtudiantEJBInterface) ctx.lookup("EtudiantEJB");
       
    }
 	 
	@Test
	public void test() throws NamingException
	{
		
		/*
		cont.setMail("aa@aa.fr");
		cont.setMailAutre("bb@bb.fr");
		cont.setTel("1234567891");
		cont.setTelAutre("9876543210");
		
		ad.setAdresse("16 rue benoit frachon");
		ad.setAdresseSuite("");
		ad.setCodePostal("62210");
		ad.setPays("France");
		ad.setVille("Avion");
		*/
		
		
		Etudiant etu = new Etudiant();
		
		etu.getContact().setMail("aa@aa.fr");
		etu.getContact().setMailAutre("bb@bb.fr");
		etu.getContact().setTel("1234567891");
		etu.getContact().setTelAutre("9876543210");
		
		etu.getAdresse().setAdresse("16 rue benoit frachon");
		etu.getAdresse().setAdresseSuite("");
		etu.getAdresse().setCodePostal("62210");
		etu.getAdresse().setPays("France");
		etu.getAdresse().setVille("Avion");
		
		etu.setNom("Toto");
		etu.setPrenom("titi");
		etu.setId(4654564L);
				
		//assertEquals(1, etuEJB2.findAllEtudiants().size());
		
		etuEJB.createEtudiant(etu);
	}

}