package projetIA.up.mi.jr;

import java.util.ArrayList;

/**
 * Représente le plateau de jeu du puissance 4
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class Plateau {
	/**
	 * tableau de type char représentant la grille du puissance 4
	 */
	char [][] grilleJeu;
	public static Plateau instance = null;
	
	/**
	 * Constructeur qui initialise la grille avec 7 colonne et 6 lignes 
	 */
	private Plateau() {
		grilleJeu = new char[6][7];
		//initialisation de la grille avec des points
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; i++) {
				grilleJeu[i][j] = '.';
			}
		}
	}
	
	/**
	 * Getteur pour récupérer le plateau du Puissance 4 qui est un singleton
	 * @return Plateau 
	 */
	public static Plateau getInstance() {
		if(instance == null) {
			instance = new Plateau();		
		}
		return instance;
	}
	
	/**
	 * Méthode pour créer un plateau de jeu vide (graphiquement)
	 */
	public void affichePlateau() {
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				System.out.println(" | " + grilleJeu[i][j]);
			}
			System.out.println(" | ");
		}
	}
	
	/**
	 * Méthode pour placer un jeton dans le plateau
	 * @param coordonnee du jeton
	 * @param couleur du jeton
	 * @throws PuissanceException  exception levé si on veut placer un jeton dans une colonne déjà pleine
	 */
	public void placerJeton(ArrayList<Integer> coordonnee, char couleur) throws PuissanceException {
		
		if (grilleJeu[1][coordonnee.get(2)] != '.') {
			throw new PuissanceException("la colonne est déja pleine");
		}
		grilleJeu[coordonnee.get(1)][coordonnee.get(2)] = couleur;
		
	}
}
