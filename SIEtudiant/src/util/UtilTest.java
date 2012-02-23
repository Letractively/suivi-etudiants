package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;


import org.junit.BeforeClass;




public class UtilTest {

	private static EJBContainer container;
    public static Context ctx;
 
    @BeforeClass
    public static void setup() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("C:/Users/Romain/workspace/SIEtudiant/build/classes"));
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
            "C:/glassfish3/glassfish");
        container = EJBContainer.createEJBContainer();
        ctx = container.getContext();
    }
}
