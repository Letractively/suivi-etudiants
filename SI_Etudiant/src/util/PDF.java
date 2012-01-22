package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
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
	public void CreerListeEtudiantsPDF(List<Etudiant> etudiants) {

		// On teste si l'utilisateur est sous Windows pour le chemin de stockage du PDF
		if (this.isWindows()) {

			// PDF sur le bureau par defaut
			String chemin = "C:\\Users\\" + this.userName()
					+ "\\Desktop\\listeEtudiants.pdf";

			try {
				// Flux de sortie -> Fichier
				OutputStream file = new FileOutputStream(new File(chemin));
				// Creation d'un document
				Document document = new Document();
				// On recupere l'instance de la classe permettant d'ecrire et on
				// lui passe le fichier et le document
				PdfWriter.getInstance(document, file);
				// Ouverture du document
				document.open();
				// Nom de l'auteur du PDF (celui qui utilise la machine)
				document.addAuthor(this.userName());
				// Date de creation
				document.addCreationDate();
				// Titre du PDF
				document.addTitle("Liste des etudiants");
				// Parcours de boucle, pour chaque etudiant de la liste
				for (Etudiant etu : etudiants) {
					// On ajoute des elements au paragraphe
					Paragraph p = new Paragraph();
					p.add(etu.getNom().toString());
					p.add(etu.getPrenom().toString());
					p.add(etu.getAdresse().getAdresse().toString());
					// Ajout du paragraphe au document
					document.add(p);
				}		
				// Fermeture du document
				document.close();
				// Fermeture du flux IO fichier
				file.close();
				// Attrapper les diverses exceptions, fichier, document et IO
			} catch (FileNotFoundException fe) {
				System.out.println(fe);
			} catch (DocumentException de) {
				System.out.println(de);
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
	}

	/*
	 * Recupere le nom de l'OS
	 */
	public String osName() {
		String name = System.getProperty("os.name");
		return name;
	}

	/*
	 * Recupere le nom de l'utilisateur
	 */
	public String userName() {
		String user = System.getProperty("user.name");
		return user;
	}

	/*
	 * Renvoie vrai si l'OS correspond a Windows, sinon faux
	 */
	public Boolean isWindows() {
		if (this.osName().equals("Windows 7")
				|| (this.osName().equals("Windows Vista"))
				|| (this.osName().equals("Windows XP"))) {
			return true;
		} else {
			return false;
		}
	}
}