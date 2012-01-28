package util;

import java.io.IOException;

import javax.faces.context.FacesContext;

/*Cette classe � pour but de rediger l'utilisateur en cas d'erreur de l'application.
 * Cette classe est une alternative � face-config.xml car passege de parametre impossible est impossible

 */

public class Redirection {
	private static String domain = "SI_Etudiant";

	public static void erreurXhtml() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/" + domain + "/erreur.faces");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void listeEtudiantEntreprise(long idEtudiant) {
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/" + domain + "/listeEtudiantEntreprise.faces?id="
									+ idEtudiant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void listeEtudiants() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/" + domain + "/listeEtudiants.faces");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void listeEntreprises() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/" + domain + "/listeEntreprises.faces");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void listeEtablissements() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/" + domain + "/listeEtablissements.faces");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void log() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/" + domain + "/site.faces");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void listeFormations(long idEtablissement) {
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/" + domain + "/listeFormations.faces?id="
									+ idEtablissement);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}