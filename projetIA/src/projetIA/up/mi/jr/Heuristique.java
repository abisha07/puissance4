package projetIA.up.mi.jr;

import java.util.ArrayList;

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
	public int evaluationbis(Plateau plateau) {
		
//		if(plateau.aGagne(plateau.getJoueurSuivant() , 4)){
//			//System.out.println("Min");
//			return MIN_SCORE;
//		}
//		
//		if(plateau.aGagne(plateau.getJoueurCourant(), 4)){
//			//System.out.println("Max");
//			return MAX_SCORE;
//		}
//		
		int res = 0;
		int color = 0;
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
				}else if(plateau.getCouleur(line, column) == plateau.getJoueurSuivant().getCouleur()) { //Cas où la case est prise par le joueur suivant
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
	
	public int evaluation(Plateau plateau) {
		int res = 0;
		for (int line = 0; line < 6; line++) {
			for (int column = 0; column < 7; column++) {
				if(plateau.getCouleur(line, column) == plateau.getJoueurCourant().getCouleur()) { //Cas où la case est prise par le joueur courant
					res += poids_cases()[line][column];
				}else if(plateau.getCouleur(line, column) == plateau.getJoueurSuivant().getCouleur()) { //Cas où la case est prise par le joueur suivant
					res -= poids_cases()[line][column];
				}
			}
		}		
		return res;
	}

	public static int[][] poids_cases() {
	    //Calcule le poids des cases en fonction de la dimension de la grille et du nombre de pions à aligner pour gagner"""
	    //[3,4,5,7,5,4,3,4,6,8,10,8,6,4,5,8,11,13,11,8,5,5,8,11,13,11,8,5,4,6,8,10,8,6,4,3,4,5,7,5,4,3] pour une grille 7x6 avec 4 pions à aligner"""
		ArrayList <Integer> poids = new ArrayList<Integer>();
		for(int i= 0; i<42; i++) {
			poids.add(0);
		}
		
		
	    // Sur les horizontales
	    for (int j= 0; j<6; j++) {
	    	for (int i= 0; i<7-4+1; i++) {
		    	for(int k = 0; k<4; k++) {
		    		poids.set(7*j+i+k, poids.get(7*j+i+k)+1); 
		    	}
		    }
	    }
	    
	    //Sur les verticales
	    for (int j= 0; j<6-4+1; j++) {
	    	for (int i= 0; i<7; i++) {
		    	for(int k = 0; k<4; k++) {
		    		poids.set(7*j+i+k*7, poids.get(7*j+i+k*7)+1); 
		    		
		    	}
		    }
	    }
	    
	    //Sur les diagonales montantes
	    for (int j= 0; j<6-4+1; j++) {
	    	for (int i= 0; i<7-4+1; i++) {
		    	for(int k = 0; k<4; k++) {
		    		poids.set(7*j+i+k*7+k, poids.get(7*j+i+k*7+k)+1); 
		    	}
		    }
	    }

	    //Sur les diagonales descendantes
	    for (int j= 4-1; j<6; j++) {
	    	for (int i= 0; i<7-4+1; i++) {
		    	for(int k = 0; k<4; k++) {
		    		poids.set(7*j+i-k*7+k, poids.get(7*j+i-k*7+k)+1); 
		    	}
		    }
	    }
	    
	    int [][] poidsCase = new int [6][7];
	   
	    int k = 0;
	    for (int i=0; i< 6; i++) {
	    	for (int j=0; j<7; j++) {
	    		
	    		poidsCase[i][j] = poids.get(k);
	    		k++;
	    	}
	    }
	 
	    return poidsCase;
	}	
	
	


}
