package util;

import java.util.Random;

public class MotDePasseAleatoire {

	private static final int LONGUEURMDP = 8;
	
	public MotDePasseAleatoire() {
		
	}
	
	/*
	 * Genere un mot de passe aleatoire de 8 caracteres
	 */
	
		
		public static String genererMotDePasse() {
			
			Random rand = new Random();
			String mdp = "";
						
			// Boucler 9 fois
			for (int i = 0; i < LONGUEURMDP; i++) {
				// Genere un rand entre 0 et i-1 (6)
				int n = rand.nextInt(9);
				// Si n inferieur à 2, le prochain caractere genere sera un chiffre
				if (n<2) {
					mdp += rand.nextInt(10);
				}
				// Sinon, ce sera une lettre, il faut caster
				else {									
					if(n<5)
					{
						mdp += (char) ('a' +rand.nextInt(26));
					}
					else
					{
						mdp += (char) ('A' +rand.nextInt(26));
					}
				}
			}

		return mdp;
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


