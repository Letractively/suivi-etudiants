package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * Classe permettant de gérer les dates aux différents formats
 */
public class DateUtil {

	// Choix de la langue francaise
	static Locale locale = Locale.getDefault();
	static DateUtil actuelle = new DateUtil();

	// Definition du format utilise pour les dates
	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public static String format(java.util.Date date) {
		return DateFormat.getDateInstance( DateFormat.MEDIUM ).format(date);
	}

	public static Date date() throws ParseException {
		Date actuelle = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dat = dateFormat.format(actuelle);
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date convertedDate = dateFormat2.parse(dat); 	
		return convertedDate;
	}
}
