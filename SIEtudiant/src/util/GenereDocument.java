package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
			OutputStream file = new FileOutputStream(new File(
					Util.cheminFichier(nomPDF, "pdf")));
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
			List<EtudiantFormation> etudiantsFormation, String nomPDF) {
		try {
			// Flux de sortie -> Fichier
			OutputStream file = new FileOutputStream(new File(
					Util.cheminFichier(nomPDF, "pdf")));
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

			document.add(new Paragraph("Etudiant "
					+ etudiant.getPrenom().toString() + " "
					+ etudiant.getNom().toString() + " :"));
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
			document.add(new Paragraph("Liste des entreprises de "
					+ etudiant.getPrenom().toString() + " "
					+ etudiant.getNom().toString() + " :"));
			document.add(new Paragraph("\n"));

			/*
			 * 2e tableau : les activites de cet etudiant
			 */
			PdfPTable tableEnt = new PdfPTable(5);
			// Largeur des colonnes
			// tableEnt.setWidths(new int[] { 16, 16, 24, 10, 10 });
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
			document.add(new Paragraph("Liste des formations de "
					+ etudiant.getPrenom().toString() + " "
					+ etudiant.getNom().toString() + " :"));
			document.add(new Paragraph("\n"));

			/*
			 * 3e tableau : les formations de l'etudiant
			 */

			PdfPTable tableForma = new PdfPTable(4); // Largeur des colonnes
			// tableForma.setWidths(new int[] { 16, 16, 24, 10, 10, 16});
			// Alignement des elements
			tableForma.setHorizontalAlignment(Element.ALIGN_CENTER);
			// Champs d'en-tete
			tableForma.addCell("ETABLISSEMENT");
			tableForma.addCell("LIBELLE");
			tableForma.addCell("DATE DEBUT");
			tableForma.addCell("DATE FIN");
			// Ajouts
			for (EtudiantFormation etuForma : etudiantsFormation) {
				tableForma.addCell(etuForma.getFormation().getEtablissement()
						.getNom().toString());
				tableForma.addCell(etuForma.getFormation().getLibelle()
						.toString());
				tableForma
						.addCell(Date.format(etuForma.getId().getDatedebut()));
				tableForma.addCell(Date.format(etuForma.getDatefin()));
			}
			// Ajout du tableau au document
			document.add(tableForma);

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
	 * Creation d'un fichier Excel contenant ma liste d'étudiants
	 */
	@SuppressWarnings("deprecation")
	public static void creerListeEtudiantsXLS(List<Etudiant> etudiants,
			String nomFichier) {

		// Creation d'un document
		HSSFWorkbook wb = new HSSFWorkbook();
		// Creation d'une feuille
		HSSFSheet sheet = wb.createSheet("liste etudiants");

		// Creation d'une ligne
		HSSFRow ligneTitre = sheet.createRow(0);
		
		// Police
		HSSFFont f = wb.createFont();
		// Gras
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// Style
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// Attribuer le style
		cellStyle.setFont(f);

		// Creer les titres des colonnes
		HSSFCell cNom = ligneTitre.createCell((short) 0, HSSFCell.CELL_TYPE_STRING);
		cNom.setCellValue(new HSSFRichTextString("NOM"));
		cNom.setCellStyle(cellStyle);
		HSSFCell cPrenom = ligneTitre.createCell((short) 1, HSSFCell.CELL_TYPE_STRING);
		cPrenom.setCellValue(new HSSFRichTextString("PRENOM"));
		cPrenom.setCellStyle(cellStyle);
		HSSFCell cAdresse = ligneTitre.createCell((short) 2, HSSFCell.CELL_TYPE_STRING);
		cAdresse.setCellValue(new HSSFRichTextString("ADRESSE"));
		cAdresse.setCellStyle(cellStyle);
		HSSFCell cCP = ligneTitre.createCell((short) 3, HSSFCell.CELL_TYPE_STRING);
		cCP.setCellValue(new HSSFRichTextString("CP"));
		cCP.setCellStyle(cellStyle);
		HSSFCell cVille = ligneTitre.createCell((short) 4, HSSFCell.CELL_TYPE_STRING);
		cVille.setCellValue(new HSSFRichTextString("VILLE"));
		cVille.setCellStyle(cellStyle);
		HSSFCell cMail = ligneTitre.createCell((short) 5, HSSFCell.CELL_TYPE_STRING);
		cMail.setCellValue(new HSSFRichTextString("MAIL"));
		cMail.setCellStyle(cellStyle);
		HSSFCell cTel = ligneTitre.createCell((short) 6, HSSFCell.CELL_TYPE_STRING);
		cTel.setCellValue(new HSSFRichTextString("TEL"));
		cTel.setCellStyle(cellStyle);

		int ligne = 1;
		// Pour chaque etudiant
		for (Etudiant e : etudiants) {
			// Creation d'une ligne
			HSSFRow ligneEtu = sheet.createRow(ligne);
			// Creation des colonnes
			ligneEtu.createCell((short) 0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getNom().toString()));
			ligneEtu.createCell((short) 1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getPrenom().toString()));
			ligneEtu.createCell((short) 2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getAdresse().getAdresse().toString()));
			ligneEtu.createCell((short) 3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getAdresse().getCodePostal().toString()));
			ligneEtu.createCell((short) 4, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getAdresse().getVille().toString()));
			ligneEtu.createCell((short) 5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getContact().getMail().toString()));
			ligneEtu.createCell((short) 6, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(e.getContact().getTel().toString()));
			// Incrementer la ligne
			ligne++;
		}

		try {
			// Flux de sortie
			OutputStream file = new FileOutputStream(new File(
					Util.cheminFichier(nomFichier, "xls")));
			// Ecriture dans le xls
			wb.write(file);
			// Fermeture de mon flux
			file.close();
			// Ouverture auto du XLS
			Util.ouvrirFichier(Util.cheminFichier(nomFichier, "xls"));
		} catch (FileNotFoundException fe) {
			System.out.println(fe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void creerEtudiantXLS(Etudiant etudiant, 
			List<EtudiantEntreprise> etudiantsEntreprise,
			List<EtudiantFormation> etudiantsFormation, String nomFichier) {
		// Creation d'un document
		HSSFWorkbook wb = new HSSFWorkbook();
		// Creation d'une feuille
		HSSFSheet sheet = wb.createSheet("etudiant " + etudiant.getNom().toString());

		/*
		 * Etudiant
		 */
		
		// Creation d'une ligne pour le titre
		HSSFRow ligneTitreEtu = sheet.createRow(0);
		
		// Titre du tableau 1
		ligneTitreEtu.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString("Etudiant : " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString()));
		// Fusion de cellules pour placer un titre
		sheet.addMergedRegion(new Region(0,(short)0,0,(short)6));
		
		// Creation d'une autre ligne pour les sous-titres
		HSSFRow ligneSousTitresEtu = sheet.createRow(1);

		// Creer les titres des colonnes
		ligneSousTitresEtu.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("NOM"));
		ligneSousTitresEtu.createCell((short) 1, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("PRENOM"));
		ligneSousTitresEtu.createCell((short) 2, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("ADRESSE"));
		ligneSousTitresEtu.createCell((short) 3, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("CP"));
		ligneSousTitresEtu.createCell((short) 4, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("VILLE"));
		ligneSousTitresEtu.createCell((short) 5, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("MAIL"));
		ligneSousTitresEtu.createCell((short) 6, HSSFCell.CELL_TYPE_STRING)
				.setCellValue(new HSSFRichTextString("TEL"));
		
		// Creation d'une ligne pour les donnees de l'etudiant
		HSSFRow ligneEtu = sheet.createRow(2);
		
		// Creation des colonnes de l'etudiant
		ligneEtu.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getNom().toString()));
		ligneEtu.createCell((short) 1, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getPrenom().toString()));
		ligneEtu.createCell((short) 2, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getAdresse().getAdresse().toString()));
		ligneEtu.createCell((short) 3, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getAdresse().getCodePostal().toString()));
		ligneEtu.createCell((short) 4, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getAdresse().getVille().toString()));
		ligneEtu.createCell((short) 5, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getContact().getMail().toString()));
		ligneEtu.createCell((short) 6, HSSFCell.CELL_TYPE_STRING)
		.setCellValue(new HSSFRichTextString(etudiant.getContact().getTel().toString()));
		
		// Passer une ligne
		HSSFRow ligneSepEtu = sheet.createRow(3);
		// Fusion des cellules
		sheet.addMergedRegion(new Region(3,(short)0,3,(short)6));
		// Cellule pour le style
		HSSFCell cSepEtu = ligneSepEtu.createCell((short) 0);
		// Creation d'une ligne de separation noire
		HSSFCellStyle styleSeparationEtu = wb.createCellStyle();
		styleSeparationEtu.setFillForegroundColor(HSSFColor.BLACK.index);
		styleSeparationEtu.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cSepEtu.setCellStyle(styleSeparationEtu);
		
		/*
		 * Entreprises rattachees a l'étudiant
		 */
		
		// Ligne pour le tire des entreprises
		HSSFRow ligneTitreEnt = sheet.createRow(4);
		
		// Titre du tableau 1
		ligneTitreEnt.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
			.setCellValue(new HSSFRichTextString("Liste des entreprises de : " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString()));
		// Fusion de cellules pour placer un titre
		sheet.addMergedRegion(new Region(4,(short)0,4,(short)4));
		
		// Creation d'une autre ligne pour les sous-titres
		HSSFRow ligneSousTitresEnt = sheet.createRow(5);
		
		// Creer les titres des colonnes
		ligneSousTitresEnt.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
						.setCellValue(new HSSFRichTextString("ENTREPRISE"));
		ligneSousTitresEnt.createCell((short) 1, HSSFCell.CELL_TYPE_STRING)
						.setCellValue(new HSSFRichTextString("DATE DEBUT"));
		ligneSousTitresEnt.createCell((short) 2, HSSFCell.CELL_TYPE_STRING)
						.setCellValue(new HSSFRichTextString("DATE FIN"));
		ligneSousTitresEnt.createCell((short) 3, HSSFCell.CELL_TYPE_STRING)
						.setCellValue(new HSSFRichTextString("POSTE OCCUPE"));
		ligneSousTitresEnt.createCell((short) 4, HSSFCell.CELL_TYPE_STRING)
						.setCellValue(new HSSFRichTextString("TYPE CONTRAT"));
		
		int ligneEtuEnt = 6;
		for(EtudiantEntreprise etuEnt : etudiantsEntreprise) {
			// Creation d'une ligne
			HSSFRow ligneEnt = sheet.createRow(ligneEtuEnt);
			// Colonnes
			ligneEnt.createCell((short) 0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(etuEnt.getEntreprise().getNom().toString()));
			ligneEnt.createCell((short) 1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(Date.format(etuEnt.getId().getDatedebut())));
			ligneEnt.createCell((short) 2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(Date.format(etuEnt.getDatefin())));
			ligneEnt.createCell((short) 3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(etuEnt.getPosteoccupe().toString()));
			ligneEnt.createCell((short) 4, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(etuEnt.getTypecontrat().toString()));
			// Incrementer la ligne
			ligneEtuEnt++;
		}
		
		// Passer une ligne (ATTENTION : on ne connait pas le nb d'entreprises, dc reprendre l'indice précédent + 1)
		HSSFRow ligneSepEnt = sheet.createRow(ligneEtuEnt);
		// Fusion des cellules
		sheet.addMergedRegion(new Region(ligneEtuEnt,(short)0,ligneEtuEnt,(short)4));
		// Cellule pour le style
		HSSFCell cSepEnt = ligneSepEnt.createCell((short) 0);
		// Creation d'une ligne de separation noire
		HSSFCellStyle styleSeparationEnt = wb.createCellStyle();
		styleSeparationEnt.setFillForegroundColor(HSSFColor.BLACK.index);
		styleSeparationEnt.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cSepEnt.setCellStyle(styleSeparationEnt);
		
		/*
		 * Formations de l'etudiant
		 */
		
		// Ligne pour le tire des formations
		HSSFRow ligneTitreForma = sheet.createRow(ligneEtuEnt + 1);
		
		// Titre du tableau 1
		ligneTitreForma.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
			.setCellValue(new HSSFRichTextString("Liste des formations de : " + etudiant.getPrenom().toString() + " " + etudiant.getNom().toString()));
		// Fusion de cellules pour placer un titre
		sheet.addMergedRegion(new Region(ligneEtuEnt + 1,(short)0,ligneEtuEnt + 1,(short)3));
		
		// Creation d'une autre ligne pour les sous-titres
		HSSFRow ligneSousTitresForma = sheet.createRow(ligneEtuEnt + 2);
		
		// Creer les titres des colonnes
		ligneSousTitresForma.createCell((short) 0, HSSFCell.CELL_TYPE_STRING)
								.setCellValue(new HSSFRichTextString("ETABLISSEMENT"));
		ligneSousTitresForma.createCell((short) 1, HSSFCell.CELL_TYPE_STRING)
								.setCellValue(new HSSFRichTextString("LIBELLE"));
		ligneSousTitresForma.createCell((short) 2, HSSFCell.CELL_TYPE_STRING)
								.setCellValue(new HSSFRichTextString("DATE DEBUT"));
		ligneSousTitresForma.createCell((short) 3, HSSFCell.CELL_TYPE_STRING)
								.setCellValue(new HSSFRichTextString("DATE FIN"));
		
		int ligneEtuForma = ligneEtuEnt + 3;
		for(EtudiantFormation etuForma : etudiantsFormation) {
			// Creation d'une ligne
			HSSFRow ligneForma = sheet.createRow(ligneEtuForma);
			// Colonnes
			ligneForma.createCell((short) 0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(etuForma.getFormation().getEtablissement().getNom().toString()));
			ligneForma.createCell((short) 1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(etuForma.getFormation().getLibelle().toString()));
			ligneForma.createCell((short) 2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(Date.format(etuForma.getId().getDatedebut())));
			ligneForma.createCell((short) 3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(Date.format(etuForma.getDatefin())));
			// Incrementer la ligne
			ligneEtuForma++;
		}
		
		try {
			// Flux de sortie
			OutputStream file = new FileOutputStream(new File(
			Util.cheminFichier(nomFichier, "xls")));
			// Ecriture dans le xls
			wb.write(file);
			// Fermeture de mon flux
			file.close();
			// Ouverture auto du XLS
			Util.ouvrirFichier(Util.cheminFichier(nomFichier, "xls"));
		} catch (FileNotFoundException fe) {
			System.out.println(fe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}