package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * Classe permettant de gérer les dates aux différents formats
 */
public class Date {

	// Choix de la langue francaise
	static Locale locale = Locale.getDefault();
	static Date actuelle = new Date();

	// Definition du format utilise pour les dates
	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public Date() {

	}
	
	public static String format(java.util.Date date) {
		return DateFormat.getDateInstance( DateFormat.MEDIUM ).format(date);
	}

	public static String date() {
		String dat = dateFormat.format(actuelle);
		return dat;
	}
}
