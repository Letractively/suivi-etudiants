package TestUnit;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import util.UtilTest;

import ejb.EtudiantEJB;
import ejbInterface.EtudiantEJBInterface;
import entity.Adresse;
import entity.Contact;
import entity.Etudiant;

public class EtudiantTest {
	
	private EtudiantEJBInterface eao;
	
	@Before
    public void setUp() throws Exception {
        eao = (EtudiantEJBInterface) new InitialContext(UtilTest.getInitProperties())
                .lookup(""
                        + "");
    }
 	 
	 
	public void test()
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
				
		
		
	}

}
