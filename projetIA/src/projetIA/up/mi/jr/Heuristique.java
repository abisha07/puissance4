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
//		ArrayList<Integer> listeCoupValide = new ArrayList<Integer>();
//		
//		
//		for(int i = 0; i <= 6; i++){
//				if(plateau.getLigneValide(i) != -1){
//					listeCoupValide.add(i);
//				}
//			} 
//		
//		for(int i : listeCoupValide) {
//			//System.out.println("listeCoupValide " + listeCoupValide);
//			
//			//DEEP COPY
//			Plateau copieJeu = plateau.copieGrille();
//				if(plateau.aGagne(plateau.getJoueur(), 3 )) {
//					System.out.println("on a coup Gagnant");
//					boolean trouveCoupGagnant = false;
//					int colonne = 0;
//					while(! trouveCoupGagnant && colonne < 7) {
//						copieJeu.placerJeton(colonne, copieJeu.getJoueur());
//						if (copieJeu.aGagne(copieJeu.getJoueur(), 4)) {
//
//							trouveCoupGagnant = true;
//							return MAX_SCORE;
//							//return colonne;
//						}					
//						copieJeu.supprimePlacement(colonne);
//						colonne++;
//						
//					}
//					
//				}
//				
//				//Contre attaque
//				
//				if(plateau.aGagne(plateau.getJoueurAdverse(), 3 )) {
//					System.out.println("on contre attaque");
//					boolean trouveContreAttaque = false;
//					int colonne = 0;
//					while(! trouveContreAttaque && colonne < 7) {
//						copieJeu.placerJeton(colonne, copieJeu.getJoueurAdverse());
//						if (copieJeu.aGagne(copieJeu.getJoueurAdverse(), 4)) {
//							trouveContreAttaque = true;
//							return MAX_SCORE;
//							//return colonne;
//						}					
//						copieJeu.supprimePlacement(colonne);
//						colonne++;
//						
//					}
//				}
//		}
		
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
	
	
	
	public int coupGagnant(Plateau plateau) throws PuissanceException {
		Plateau copieJeu = plateau.copieGrille();
		//System.out.println("gagnant " +copieJeu.getJoueur().getCouleur());
		if(plateau.aGagne(plateau.getJoueur(), 3 )) {
			//System.out.println("on a coup Gagnant");
			boolean trouveCoupGagnant = false;
			int colonne = 0;
			while(! trouveCoupGagnant && colonne < 7) {
				copieJeu.placerJeton(colonne, copieJeu.getJoueur());
				if (copieJeu.aGagne(copieJeu.getJoueur(), 4)) {

					trouveCoupGagnant = true;
					//return true;
					return colonne;
				}					
				copieJeu.supprimePlacement(colonne);
				colonne++;
				
			}
			
		}
		return 0;
	}
	
	
	public int coupPerdant(Plateau plateau) throws PuissanceException { //contre attaque
		Plateau copieJeu = plateau.copieGrille();
		//System.out.println("perdant " +copieJeu.getJoueurAdverse().getCouleur());
		if(plateau.aGagne(plateau.getJoueurAdverse(), 3 )) {
			//System.out.println("on contre attaque");
			boolean trouveContreAttaque = false;
			int colonne = 0;
			while(! trouveContreAttaque && colonne < 7) {
				copieJeu.placerJeton(colonne, copieJeu.getJoueurAdverse());
				if (copieJeu.aGagne(copieJeu.getJoueurAdverse(), 4)) {
					trouveContreAttaque = true;
					//return true;
					return colonne;
				}					
				copieJeu.supprimePlacement(colonne);
				colonne++;
				
			}
		}
		return 0;
		
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
