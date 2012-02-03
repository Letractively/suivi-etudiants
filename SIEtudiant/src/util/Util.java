package util;

import java.io.IOException;

/*
 * Classe utilitaire pour le projet
 * Contient toutes les methodes utiles pour les generations de documents
 */
public class Util {
	
	public Util() {
		
	}
	
	/*
	 * Recupere le nom de l'OS
	 */
	public static String osName() {
		String name = System.getProperty("os.name");
		return name;
	}

	/*
	 * Recupere le nom de l'utilisateur
	 */
	public static String userName() {
		String user = System.getProperty("user.name");
		return user;
	}

	/*
	 * Renvoie vrai si l'OS correspond a Windows, sinon faux
	 */
	public static Boolean isWindows() {
		if (osName().equals("Windows 7") || (osName().equals("Windows Vista"))
				|| (osName().equals("Windows XP"))) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Chemin de mon fichier
	 */
	public static String cheminFichier(String nom, String extension) {
		String chemin = "";
		if (isWindows()) {
			chemin = "C:\\Users\\" + userName() + "\\Desktop\\" + nom
					+ "." + extension;
		} else {
			/*
			 * A FINIR
			 */
			if (osName().equals("Ubuntu")) {
				System.out.println(osName());
				chemin = "/home/" + userName();
			}
		}
		return chemin;
	}

	/*
	 * Ouvre un fichier automatiquement sous Windows
	 * 
	 * @param chemin du fichier
	 */
	public static void ouvrirFichier(String chemin) {
		if (isWindows()) {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("cmd /c " + chemin);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
