package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.*;

/*
 * Classe qui va utiliser i-text et POI pour mes generations de documents
 */
public class GenereDocument {

	public GenereDocument() {

	}
	
	/*
	 * METHODES POUR LES DOCUMENTS PDF
	 */

	/*
	 * Cree un PDF contenant une liste d'etudiants
	 * 
	 * @param : Liste d'etudiants
	 */
	public static void creerListeEtudiantsPDF(List<Etudiant> etudiants,
			String nomPDF) {

		try {
			// Flux de sortie -> Fichier
			OutputStream file = new FileOutputStream(
					new File(Util.cheminFichier(nomPDF, "pdf")));
			// Creation d'un document
			Document document = new Document();
			// On recupere l'instance de la classe permettant d'ecrire et on
			// lui passe le fichier et le document
			PdfWriter.getInstance(document, file);
			// Ouverture du document
			document.open();
			// Nom de l'auteur du PDF (celui qui utilise la machine)
			document.addAuthor(Util.userName());
			// Date de creation
			document.addCreationDate();
			// Titre du PDF
			document.addTitle("Liste des etudiants");
			// Tableau avec x-colonnes
			PdfPTable table = new PdfPTable(7);
			// Largeur des colonnes
			table.setWidths(new int[] { 16, 16, 24, 10, 10, 16, 16 });
			// Alignement des elements
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			table.addCell("NOM");
			table.addCell("PRENOM");
			table.addCell("ADRESSE");
			table.addCell("CP");
			table.addCell("VILLE");
			table.addCell("MAIL");
			table.addCell("TEL");
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
			}
			;
			// Ajout du paragraphe au document
			document.add(table);
			// Fermeture du document
			document.close();
			// Fermeture du flux IO fichier
			file.close();

			// Ouvrir mon fichier
			Util.ouvrirFichier(Util.cheminFichier(nomPDF, "pdf"));

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
	 * Cree un PDF de l'etudiant avec ses activites et ses formations
	 */
	public static void creerEtudiantPDF(Etudiant etudiant,
			List<EtudiantEntreprise> etudiantsEntreprise,
			String nomPDF) {
		try {
			// Flux de sortie -> Fichier
			OutputStream file = new FileOutputStream(
					new File(Util.cheminFichier(nomPDF, "pdf")));
			// Creation d'un document
			Document document = new Document();
			// On recupere l'instance de la classe permettant d'ecrire et on
			// lui passe le fichier et le document
			PdfWriter.getInstance(document, file);
			// Ouverture du document
			document.open();
			// Nom de l'auteur du PDF (celui qui utilise la machine)
			document.addAuthor(Util.userName());
			// Date de creation
			document.addCreationDate();
			// Titre du PDF
			document.addTitle("Liste des entreprises et des formations de l'etudiant");

			document.add(new Paragraph("Etudiant " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString() + " :"));
			document.add(new Paragraph("\n"));
			
			/*
			 * 1er tableau : l'etudiant
			 */
			PdfPTable tableEtu = new PdfPTable(7);
			// Largeur des colonnes
			tableEtu.setWidths(new int[] { 16, 16, 24, 10, 10, 16, 16 });
			// Alignement des elements
			tableEtu.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			tableEtu.addCell("NOM");
			tableEtu.addCell("PRENOM");
			tableEtu.addCell("ADRESSE");
			tableEtu.addCell("CP");
			tableEtu.addCell("VILLE");
			tableEtu.addCell("MAIL");
			tableEtu.addCell("TEL");
			// Ajouts
			tableEtu.addCell(etudiant.getNom().toString());
			tableEtu.addCell(etudiant.getPrenom().toString());
			tableEtu.addCell(etudiant.getAdresse().getAdresse().toString());
			tableEtu.addCell(etudiant.getAdresse().getCodePostal().toString());
			tableEtu.addCell(etudiant.getAdresse().getVille().toString());
			tableEtu.addCell(etudiant.getContact().getMail().toString());
			tableEtu.addCell(etudiant.getContact().getTel().toString());
			// Ajout du tableau au document
			document.add(tableEtu);
			
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Liste des entreprises de " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString() + " :"));
			document.add(new Paragraph("\n"));

			/*
			 * 2e tableau : les activites de cet etudiant
			 */
			PdfPTable tableEnt = new PdfPTable(5);
			// Largeur des colonnes
			//tableEnt.setWidths(new int[] { 16, 16, 24, 10, 10 });
			// Alignement des elements
			tableEnt.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			tableEnt.addCell("ENTREPRISE");
			tableEnt.addCell("DATE DEBUT");
			tableEnt.addCell("DATE FIN");
			tableEnt.addCell("POSTE OCCUPE");
			tableEnt.addCell("TYPE CONTRAT");
			// Ajouts
			for (EtudiantEntreprise etuEnt : etudiantsEntreprise) {
				tableEnt.addCell(etuEnt.getEntreprise().getNom().toString());
				tableEnt.addCell(Date.format(etuEnt.getId().getDatedebut()));
				tableEnt.addCell(Date.format(etuEnt.getDatefin()));
				tableEnt.addCell(etuEnt.getPosteoccupe().toString());
				tableEnt.addCell(etuEnt.getTypecontrat().toString());
			}
			// Ajout du tableau au document
			document.add(tableEnt);
			
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Liste des formations de " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString() + " :"));
			document.add(new Paragraph("\n"));
			
			/*
			 * 3e tableau : les formations de l'etudiant
			 */
			/*PdfPTable tableForma = new PdfPTable(4);
			// Largeur des colonnes
			//tableForma.setWidths(new int[] { 16, 16, 24, 10, 10, 16});
			// Alignement des elements
			tableForma.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			tableForma.addCell("ETABLISSEMENT");
			tableForma.addCell("LIBELLE");
			tableForma.addCell("DATE DEBUT");
			tableForma.addCell("DATE FIN");
			// Ajouts
			for (EtudiantFormation etuForma : etudiantsFormation) {
				tableForma.addCell(etuForma.getFormation().getEtablissement().getNom().toString());
				tableForma.addCell(etuForma.getFormation().getLibelle().toString());
				tableForma.addCell(Date.format(etuForma.getId().getDatedebut()));
				tableForma.addCell(Date.format(etuForma.getDatefin()));
			}
			// Ajout du tableau au document
			document.add(tableForma);*/
			
			// Fermeture du document
			document.close();
			// Fermeture du flux IO fichier
			file.close();

			// Ouvrir mon fichier
			Util.ouvrirFichier(Util.cheminFichier(nomPDF, "pdf"));

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
	 * METHODES POUR LES DOCUMENTS EXCEL
	 */
	
	/*
	 * Methode de test
	 */
	public static void main(String[] args) {

		HSSFWorkbook wb = new HSSFWorkbook();
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File(Util.cheminFichier("Test", "xls")));
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}