package projetIA.up.mi.jr;

/**
 * Représente l'ensemble des heuristiques utilisées dans le jeu Puissance 4
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class Heuristique {
	/**
	 * Une heuristique est composée d'une valeur Min et d'une valeur Max
	 */
	private int MAX_SCORE;
	private int MIN_SCORE;
	
	/**
	 * Constructeur
	 */
	public Heuristique() {
		MAX_SCORE = Integer.MAX_VALUE;
		MIN_SCORE = Integer.MIN_VALUE;
	}
	
	/**
	 * Heuristique aléatoire
	 * @return Une valeur aléatoire
	 */
	public double randomHeuristique() {
		return Math.random();
	}
	
	/**
	 * Getteur de la valeur Max de l'heuristique
	 * @return La valeur de MAX_SCORE
	 */
	public int getMaxScore() {
		return MAX_SCORE;
	}
	
	/**
	 * Getteur de la valeur Min de l'heuristique
	 * @return La valeur de MIN_SCORE
	 */
	public int getMinScore() {
		return MIN_SCORE;
	}
	

//	public double noteGrille2(Plateau plateau) throws PuissanceException {
//			
//			
//			
//	
//		int colonne = plateau.joueur.derniereColonne;
//		int ligne = plateau.getLineValid(colonne)+1;
//		
//		int colonneS = plateau.joueurSuivant.derniereColonne;
//		int ligneS = plateau.getLineValid(colonneS)+1;
////		char couleurJoueurSuivant;
////		if (joueur.getCouleur() == 'R') {
////			couleurJoueurSuivant = 'J';
////		}else {
////			couleurJoueurSuivant = 'R';
////		}
//		
//		if(plateau.chaine_max(ligneS, colonneS, plateau.joueurSuivant.getCouleur()) >= 4){
//			return MIN_SCORE;
//		}
//		
//		if(plateau.chaine_max(ligne, colonne, plateau.joueur.getCouleur()) >= 4){
//			return MAX_SCORE;
//		}
//			
//			double resultat = 0;
//			
//			for(int i = 1; i <= 6; i++){
//				for(int j = 1; j <= 7; j++){
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j,0, 1) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, 1, 1) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, 1, 0) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, 1, -1) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, 0, -1) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, -1, -1) ;
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j, -1, 0) ; 
//					resultat +=plateau.resultatAlignementDeJeton(plateau.joueur.getCouleur(), i, j,-1, 1) ;
//					
//				}
//			}
//	
//			return resultat;
//		}
	
//	/**
//	 * Heuristique évaluant toute la grille de Jeu
//	 * @param plateau Objet correspondant à l'état de jeu actuel
//	 * @return Une valeur d'heuristique
//	 * @throws PuissanceException
//	 */
//	public double noteGrille(Plateau plateau) throws PuissanceException {
//			int colonne = plateau.joueur.derniereColonne; // Récupère la position du dernier jeton placé par le premier joueur
//			int ligne = plateau.getLineValid(colonne)+1;		
//			
//			if(plateau.hasWon(plateau.joueurSuivant, 3)){
//				//System.out.println("Min");
//				return MIN_SCORE;
//			}
//			
//			if(plateau.hasWon(plateau.joueur, 3)){
//				//System.out.println("Max");
//				return MAX_SCORE;
//			}
//			
//			
//			int score = plateau.chaine_max(ligne, colonne, plateau.joueur.getCouleur());
//			int score2Jetons = 0;
//			int score3Jetons = 0;
//			int score4Jetons = 0;
//			switch (score) {
//			case 2:	
//				score2Jetons = score;
//				
//				break;
//				
//			case 3:
//				score3Jetons = 3*score;
//				break;
//			
//			case 4:
//				score4Jetons = 9*score;
//				break;
//			}			
//			return score2Jetons + score3Jetons + score4Jetons;
//		}
	
	/**
	 * Heuristique évaluant toute la grille de Jeu
	 * @param plateau Objet correspondant à l'état de jeu actuel
	 * @return Une valeur d'heuristique
	 */
	public int evaluation(Plateau plateau) {
		
//		if(plateau.aGagne(plateau.joueurSuivant, 4)){
//			//System.out.println("Min");
//			return MIN_SCORE;
//		}
//		
//		if(plateau.aGagne(plateau.joueur, 4)){
//			//System.out.println("Max");
//			return MAX_SCORE;
//		}
//		
		int res = 0;
		int color;
		for (int line = 0; line < 6; line++) {
			for (int column = 0; column < 7; column++) {
//				System.out.println("**********");
//				plateau.affichePlateau();
//				System.out.println("**********");
				if (plateau.getCouleur(line, column) == '.') { //Cas où la case est vide
					color = 0;
				}else if(plateau.getCouleur(line, column) == plateau.getJoueurCourant().getCouleur()) { //Cas où la case est prise par le joueur courant
					//System.out.println("IIIIIIIIIIIIIIIIIIIIII");
					color = 1;
				}else { //Cas où la case est prise par le joueur suivant
					//System.out.println("00000000000000");
					color = -1;
				}
				if (color != 0) {  //Cas où la case n'est pas vide
					res += color * plateau.chaine_max(line, column, plateau.getJoueurCourant().getCouleur());
				}
			}
		}
//		System.out.println("**********");
//		plateau.affichePlateau();
//		System.out.println("**********");
//		System.out.println("Joueur courant : " + plateau.getJoueurCourant());
//		System.out.println("evaluation : " + res);
		return res;
	}

	

}
