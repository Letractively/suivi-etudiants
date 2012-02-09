package junit;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;


import org.junit.BeforeClass;
import org.junit.Test;

import util.UtilTest;

import ejb.EtudiantEJB;
import entity.Adresse;
import entity.Contact;
import entity.Etudiant;

public class EtudiantTest {
	
	private static EJBContainer container;
	private static Context ctx;
	
	@BeforeClass
    public static void setUp() throws Exception {
        
		Map p = new HashMap();
        
        p.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                "C:/glassfish3/glassfish");
       
		
        //container = EJBContainer.createEJBContainer(UtilTest.getInitProperties());
		container = EJBContainer.createEJBContainer(p);
        ctx = container.getContext();
	}
 	 
	@Test
	public void test() throws NamingException
	{
		
		Contact cont=new Contact();
		Adresse ad=new Adresse();
		
		
		cont.setMail("aa@aa.fr");
		cont.setMailAutre("bb@bb.fr");
		cont.setTel("1234567891");
		cont.setTelAutre("9876543210");
		
		ad.setAdresse("16 rue benoit frachon");
		ad.setAdresseSuite("");
		ad.setCodePostal("62210");
		ad.setPays("France");
		ad.setVille("Avion");
		
		Etudiant etu = new Etudiant();
		etu.setNom("Toto");
		etu.setPrenom("titi");
		etu.setContact(cont);
		etu.setAdresse(ad);
				
		
		EtudiantEJB etuEJB=(EtudiantEJB) ctx.lookup("java:global/classes/EtudiantEJB");
		
		etuEJB.createEtudiant(etu);
	}

}