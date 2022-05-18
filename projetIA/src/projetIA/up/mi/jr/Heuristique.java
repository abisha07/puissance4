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
	

	/**
	 * Heuristique évaluant toute la grille de Jeu
	 * @param plateau Objet correspondant à l'état de jeu actuel
	 * @return Une valeur d'heuristique
	 * @throws PuissanceException 
	 */
	public int evaluation(Plateau plateau) throws PuissanceException {		
		int res = 0;
		for (int line = 0; line < 6; line++) {
			for (int column = 0; column < 7; column++) {
				if(plateau.getCouleur(line, column) == plateau.getJoueurCourant().getCouleur()) { //Cas où la case est prise par le joueur Max
					res += poids_cases()[line][column];
				}else if(plateau.getCouleur(line, column) == plateau.getJoueurSuivant().getCouleur()) { //Cas où la case est prise par le joueur Min
					res -= poids_cases()[line][column];
				}
			}
		}
		return res;
	}
	
	
	
	public boolean coupGagnant(Plateau copieJeu, int colonne) throws PuissanceException {
		if(copieJeu.aGagne(copieJeu.getJoueurCourant(), 3 )) {
				copieJeu.placerJeton(colonne, copieJeu.getJoueurCourant());
				if (copieJeu.aGagne(copieJeu.getJoueurSuivant(), 4)) {
					copieJeu.supprimePlacement(colonne);
					return true;
				}					
				copieJeu.supprimePlacement(colonne);
				
			}
			
		return false;
	}
	
	
	public boolean coupPerdant(Plateau copieJeu, int colonne) throws PuissanceException { //contre attaque
		if(copieJeu.aGagne(copieJeu.getJoueurSuivant(), 3 )) {
			copieJeu.placerJeton(colonne, copieJeu.getJoueurSuivant());
				if (copieJeu.aGagne(copieJeu.getJoueurCourant(), 4)) {
					copieJeu.supprimePlacement(colonne);
					return true;
				}					
				copieJeu.supprimePlacement(colonne);
				colonne++;
				
			}
		return false;
		
	}

	/**
	 * Fontion calculant le poids des cases pour une grille classique de Puissance 4
	 * @return
	 */
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
