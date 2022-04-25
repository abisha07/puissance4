package projetIA.up.mi.jr;

import java.util.ArrayList;
import java.util.Arrays;

public class IAMinimax extends Joueur{
	
	//private int niveau = 1;
	private int profondeur;
	//private char couleurJeton;
	
	private Heuristique heuristique;
	
	public IAMinimax(char couleurJeton, int numJoueur, int profondeur) {
		super(couleurJeton, numJoueur);
		this.profondeur = profondeur;
		heuristique = new Heuristique();
		
	}
	

	@Override
	public int trouverPlacement(Plateau plateau) {
		int colonneAJouer = 1;

		double valeurDeJeu = heuristique.getMinScore();

		for(int i=1; i<= 6; i++) {
			if(plateau.grilleJeu[0][i -1] == '.'){
				//DEEP COPY
				char [][] copieGrille = new char[6][7];
				for (int t=0;t<copieGrille.length;t++) {
					copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
				} 
				
				Plateau copieJeu = new Plateau(copieGrille, plateau.joueur, plateau.joueurSuivant);
				try {
					copieJeu.placerJeton(i, this);
					double valeurDeJeuCourante = minmax( copieJeu,  this, profondeur);
					if (valeurDeJeuCourante >= valeurDeJeu) {
						valeurDeJeu = valeurDeJeuCourante;
						colonneAJouer = i;
					}
				} catch (PuissanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
		
		return colonneAJouer;
	}
	
	
	

//	public int trouverPlacement2(Plateau plateau) {
//		
//		int colonneAJouer = 4; // IA cherche à placer son premier pion dans la colonne du milieu
//		
//		for(int i=1; i<= 6; i++) {
//			if(plateau.grilleJeu[0][i -1] == '.'){
//				//DEEP COPY
//				char [][] copieGrille = new char[6][7];
//				for (int t=0;t<copieGrille.length;t++) {
//					copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
//				} 
//				
//				Plateau copieJeu = new Plateau(copieGrille);
//				try {
//					copieJeu.placerJeton(colonneAJouer, this);
//					double valeurDeJeuCourante = minmax( copieJeu,  this, profondeur);
//					if (valeurDeJeuCourante >= valeurDeJeu) {
//						valeurDeJeu = valeurDeJeuCourante;
//						colonneAJouer = i;
//					}
//				} catch (PuissanceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				}
//			}
//		
//		return colonneAJouer;
//	}
	
	public double min(Plateau plateau, int profondeur) throws PuissanceException {

		if(profondeur != 0){
			
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
							
						Plateau copieJeu = new Plateau(copieGrille, plateau.joueur, plateau.joueurSuivant);
						if (copieJeu.isCoupValid(i) && copieJeu.getLineValid(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.joueur);
						}
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, profondeur-1));
					}
			
			}
			return valeurDeJeu;
		}else{
			return heuristique.randomHeuristique();  
		}
	}
	
	
	
	public double max(Plateau plateau, int profondeur) throws PuissanceException {
		
		if(profondeur != 0){
			
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
							
						Plateau copieJeu = new Plateau(copieGrille, plateau.joueur, plateau.joueurSuivant);
						if (copieJeu.isCoupValid(i) && copieJeu.getLineValid(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.joueur);
						}
						valeurDeJeu = Math.max(valeurDeJeu, this.min(copieJeu,  profondeur-1));
					}
			
			}
			return valeurDeJeu;
		}else{
			return heuristique.randomHeuristique();  
		}
	}

	
	public double minmax(Plateau plateau, Joueur joueur, int profondeur) throws PuissanceException {
		return min(plateau, profondeur);
		
	}
	
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}
	
	//BROUILLON
	
	
	public ArrayList poids_cases() {
	    //Calcule le poids des cases en fonction de la dimension de la grille et du nombre de pions à aligner pour gagner"""
	    //[3,4,5,7,5,4,3,4,6,8,10,8,6,4,5,8,11,13,11,8,5,5,8,11,13,11,8,5,4,6,8,10,8,6,4,3,4,5,7,5,4,3] pour une grille 7x6 avec 4 pions à aligner"""
	    ArrayList<Integer> poids = new ArrayList(42);
	    int contenu;
	    // Sur les horizontales
	    for (int i=0; i<6; i++) {
	    	for(int j=0; j<7-4+1; j++) {
	    		for (int k=0; k< 4; k++) {
	    			contenu = poids.get(7*j+i+k);
	    			poids.add(7*i+j+k,contenu+1);
	    		}
	    	}
	   }
	   //Sur les verticales
	    for (int i=0; i<6-4+1; i++) {
	    	for(int j=0; j<7; j++) {
	    		for (int k=0; k< 4; k++) {
	    			contenu = poids.get(7*j+i+k*7);
	    			poids.add(7*i+j+k*7,contenu+1);
	    		}
	    	}
	   }
	   //Sur les diagonales montantes 
	    for (int i=0; i<6-4+1; i++) {
	    	for(int j=0; j<7-4+1; j++) {
	    		for (int k=0; k< 4; k++) {
	    			contenu = poids.get(7*i+j+k*7);
	    			poids.add(7*i+j+k*7,contenu+1);
	    		}
	    	}
	   }
	  //Sur les diagonales descendantes
	    for (int i=0; i<4+1; i++) {
	    	for(int j=0; j<7-4+1; j++) {
	    		for (int k=0; k< 4; k++) {
	    			contenu = poids.get(7*i+j-k*7);
	    			poids.add(7*i+j-k*7,contenu+1);
	    		}
	    	}
	   }
	   return poids;	
	}
	
	
//	public int jouer_ordi_poids_cases(Plateau plateau, Joueur joueur) {
//		
//	    //L'ordinateur joue en ne tenant compte que du poids des cases de la grille potentiellement victorieuses"""
//	    poidsCases = poids_cases()
//	    poidsColonnes = [0] * NB_COLONNES
//	    for colonne in range(1, NB_COLONNES + 1):
//	        if not colonne_pleine(positions, colonne):
//	            position = colonne - 1
//	            while positions[position]:
//	                position += NB_COLONNES
//	            poidsColonnes[colonne - 1] += poidsCases[position]
//	        else:
//	            poidsColonnes[colonne - 1] += 0
//	    indicesPoidsMaximum = liste_indices_maximum(poidsColonnes)
//	    //Si plusieurs colonnes sont possibles (même poids), on tire au hasard une colonne
//	    colonne = 1 + random.choice(indicesPoidsMaximum)
//	    return jouer(positions, couleur, colonne)
//	}
}
