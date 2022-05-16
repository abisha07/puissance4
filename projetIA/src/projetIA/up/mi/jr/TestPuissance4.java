package projetIA.up.mi.jr;

import java.util.ArrayList;

public class TestPuissance4 {

	public static void affichePlateau(int[][] ks) {
		
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				System.out.print(" | " + ks[i][j]);
			}
			System.out.println(" | ");
		}
		//System.out.println();
	}
	
	public static int[][] poids_cases() {
	    //Calcule le poids des cases en fonction de la dimension de la grille et du nombre de pions à aligner pour gagner"""
	    //[3,4,5,7,5,4,3,4,6,8,10,8,6,4,5,8,11,13,11,8,5,5,8,11,13,11,8,5,4,6,8,10,8,6,4,3,4,5,7,5,4,3] pour une grille 7x6 avec 4 pions à aligner"""
	    //int NB_COLONNES = 7;
	    //int NB_LIGNES = 6;
		ArrayList <Integer> poids = new ArrayList<Integer>();
//	    int [][] poids;
//	    for (int i=0; i< 6; i++) {
//	    	for (int j=0; j<7; j++) {
//	    		poids[i][j] = 0;
//	    	}
//	    }
		for(int i= 0; i<42; i++) {
			poids.add(0);
		}
		
		
	    // Sur les horizontales
	    for (int j= 0; j<6; j++) {
	    	for (int i= 0; i<7-4+1; i++) {
		    	for(int k = 0; k<4; k++) {
		    		poids.set(7*j+i+k, poids.get(7*j+i+k)+1); 
		    		//System.out.println("aj" + poids.get(7*j+i+k));
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
		    		//System.out.println("aj" + poids.get(7*j+i-k*7+k));
		    	}
		    }
	    }
	    
	    int [][] poidsCase = new int [6][7];
	   
	    int k = 0;
	    for (int i=0; i< 6; i++) {
	    	for (int j=0; j<7; j++) {
	    		
	    		poidsCase[i][j] = poids.get(k);
	    		//System.out.println(poidsCase[i][j]);
	    		k++;
	    	}
	    }
	    return poidsCase;
	}

	
	public static void main(String[] args) throws PuissanceException {
		// TODO Auto-generated method stub
		char [][] grilleJeu = new char [6][7];
		for(int j=0; j<6; j++) {
			for(int i = 0; i<7; i++) {
				grilleJeu[j][i] = '.';
			}
		}
//		affichePlateau(grilleJeu);	
//		grilleJeu[1][0] = 'J';
//		grilleJeu[2][0] = 'J';
//		grilleJeu[3][0] = 'J';
//		grilleJeu[4][0] = 'J';
//		grilleJeu[4][1] = 'R';
//		grilleJeu[5][0] = 'R';
//		grilleJeu[5][1] = 'R';
//		System.out.println("***********");
//		affichePlateau(grilleJeu);
//		System.out.println("***********");
	JoueurHumain joueur1 = new JoueurHumain('J', 1);
	JoueurHumain joueur2 = new JoueurHumain('R', 2);
	Plateau plateau = new Plateau(joueur1, joueur2, 1);
	plateau.affichePlateau();
	Heuristique h = new Heuristique();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(5, 0,'J');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(4, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(3, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(2, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	
	
//		int ligne = plateau.getLigneValide(0);
//		//System.out.println(ligne+1);
//		System.out.println(grilleJeu[ligne+1][0]);
//		 
//		plateau.supprimePlacement(0);
//		
//		System.out.println(grilleJeu[ligne+1][0]);
//		plateau.affichePlateau();
//		System.out.println(plateau.estVide());
//		//System.out.println(plateau.resultatAlignementDeJeton('J', 1 ,0, 0,1));
//		//System.out.println(plateau.chercheAlignementDeJeton('J',2, 1, 0, 1));
		
		System.out.println("BROULIILON");
		//poids_cases();
		affichePlateau(poids_cases());
		
		
	}
	
	

}


