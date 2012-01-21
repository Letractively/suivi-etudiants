package util;

import java.io.IOException;

import javax.faces.context.FacesContext;

/*Cette classe à pour but de rediger l'utilisateur en cas d'erreur de l'application.
 * Cette classe est une alternative à face-config.xml car passege de parametre impossible est impossible

 */
	
public class Redirection
{
	private static String domain="SI_Etudiant"; 
	
	public static void erreurXhtml () 
	 {
		 try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("/"+domain+"/erreur.faces");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	 }
	 
	 public static void listeEtudiantEntreprise(long idEtudiant)
	 {
		try 
	 	{
			FacesContext.getCurrentInstance().getExternalContext().redirect("/"+domain+"/listeEtudiantEntreprise.faces?id="+idEtudiant);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void listeEtudiants()
	 {
		try 
	 	{
			FacesContext.getCurrentInstance().getExternalContext().redirect("/"+domain+"/listeEtudiants.faces");
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}