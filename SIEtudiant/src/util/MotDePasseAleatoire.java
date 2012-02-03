package util;

import java.util.Random;

public class MotDePasseAleatoire {

	private static final int LONGUEUR_MOT_DE_PASSE = 8;
	
	public MotDePasseAleatoire() {
		
	}
	
	/*
	 * Genere un mot de passe aleatoire de 8 caracteres
	 */
	public static String genererMotDePasse() {
		
		Random rand = new Random();
		String motDePasse = "";
		char lettre;
		
		// Boucler 8 fois
		for (int i = 0; i < LONGUEUR_MOT_DE_PASSE; i++) {
			// Genere un rand entre 0 et i-1 (6)
			int n = rand.nextInt(7);
			// Si n vaut 0, le prochain caractere genere sera un chiffre
			if (n==0) {
				motDePasse += rand.nextInt(10);
			}
			// Sinon, si n est compris entre 1 et 3, ce sera une lettre majuscule
			else if (n >= 1 && n <= 3) {
				lettre = (char) ('A' + rand.nextInt(26));
				motDePasse += lettre;
			}
			// Sinon, ce sera une lettre minuscule
			else {
				lettre = (char) ('a' + rand.nextInt(26));
				motDePasse += lettre;
			}
		}
		return motDePasse;
	}
	
	/*
	 * Methode main pour tester
	 */
	public static void main(String args[]) {
		for(int i = 1; i < 10; i++) {
			System.out.println(MotDePasseAleatoire.genererMotDePasse());
		}
	}
}


