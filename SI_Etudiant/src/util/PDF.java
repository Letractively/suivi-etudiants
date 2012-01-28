package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Etudiant;

public class PDF {

	public PDF() {

	}

	/*
	 * Cree un PDF contenant une liste d'etudiants
	 * 
	 * @param : Liste d'etudiants
	 */
	public static void CreerListeEtudiantsPDF(List<Etudiant> etudiants, String nomPDF) {

		try {
			// Flux de sortie -> Fichier
			OutputStream file = new FileOutputStream(new File(cheminPDF(nomPDF)));
			// Creation d'un document
			Document document = new Document();
			// On recupere l'instance de la classe permettant d'ecrire et on
			// lui passe le fichier et le document
			PdfWriter.getInstance(document, file);
			// Ouverture du document
			document.open();
			// Nom de l'auteur du PDF (celui qui utilise la machine)
			document.addAuthor(userName());
			// Date de creation
			document.addCreationDate();
			// Titre du PDF
			document.addTitle("Liste des etudiants");
			// Tableau avec x-colonnes
			PdfPTable table = new PdfPTable(7);
			// Largeur des colonnes
			table.setWidths(new int[]{16, 16, 24, 10, 10, 16, 16});
			// Alignement des elements
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			table.addCell("Nom");
			table.addCell("Prenom");
			table.addCell("Adresse");
			table.addCell("CP");
			table.addCell("Ville");	
			table.addCell("Mail");
			table.addCell("Tel");
			// Parcours de boucle, pour chaque etudiant de la liste
			for (Etudiant etu : etudiants) {
				// Ajouts
				table.addCell(etu.getNom().toString());
				table.addCell(etu.getPrenom().toString());
				table.addCell(etu.getAdresse().getAdresse().toString());
				table.addCell(etu.getAdresse().getCodePostal().toString());
				table.addCell(etu.getAdresse().getVille().toString());
				table.addCell(etu.getContact().getMail().toString());
				table.addCell(etu.getContact().getTel().toString());				
			};
			// Ajout du paragraphe au document
			document.add(table);
			// Fermeture du document
			document.close();
			// Fermeture du flux IO fichier
			file.close();

			// Ouvrir mon fichier
			ouvrirPDF(cheminPDF(nomPDF));

			// Attrapper les diverses exceptions, fichier, document et IO
		} catch (FileNotFoundException fe) {
			System.out.println(fe);
		} catch (DocumentException de) {
			System.out.println(de);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
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
		if (osName().equals("Windows 7")
				|| (osName().equals("Windows Vista"))
				|| (osName().equals("Windows XP"))) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Chemin de mon PDF
	 */
	public static String cheminPDF(String nomPDF) {
		String chemin = "";
		if (isWindows()) {
			chemin = "C:\\Users\\" + userName() + "\\Desktop\\" + nomPDF
					+ ".pdf";
		} else {
			if (osName().equals("Ubuntu")) {
				System.out.println(osName());
				chemin = "/home/" + userName();
			}
		}
		return chemin;
	}

	/*
	 * Ouvre un fichier automatiquement sous Windows
	 * @param chemin du fichier
	 */
	public static void ouvrirPDF(String chemin) {
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