package util;

import java.util.Properties;


public class UtilTest {

	public static Properties getInitProperties() {
        Properties p = new Properties();
        
        // We need to tell the context where and how to look
        p.put("", "");
        p.put("", "");
        p.put("", "");
 
        return p;
    }
}
