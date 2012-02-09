package util;

import java.util.HashMap;
import java.util.Map;


public class UtilTest {

	public static Map getInitProperties() {
		Map p = new HashMap();
        
        p.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                "C:/glassfish3/glassfish/domains/domain1");
       
        return p;
    }
}
