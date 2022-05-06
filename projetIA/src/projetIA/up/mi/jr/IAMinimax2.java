package projetIA.up.mi.jr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class IAMinimax2 extends Joueur{
	//private int niveau = 1;
		private int profondeur;
		//private char couleurJeton;
		
		private Heuristique heuristique;
		
		public IAMinimax2(char couleurJeton, int numJoueur, int profondeur) {
			super(couleurJeton, numJoueur);
			this.profondeur = profondeur;
			heuristique = new Heuristique();
			
		}
		

		
//		public int trouverPlacement3(Plateau plateau) throws PuissanceException {
//			int colonneAJouer;
//			if (plateau.isEmpty()) {
//				return 3;
//			}else {
//				colonneAJouer = 1;
//			}
//			ArrayList<Integer> listCoup = new ArrayList<Integer>();
//			for(int i = 0; i < 7; i++){
//				if (plateau.isCoupValid(i)) {
//					listCoup.add(i);
//				}
//			}
//			
//			Collections.shuffle(listCoup);
//
//			double valeurDeJeu = heuristique.getMinScore();
//
//			for(int i=1; i<=6; i++) {
//				if (listCoup.contains(i)) {
//				if(plateau.grilleJeu[0][i-1] == '.'){
//					//DEEP COPY
//					char [][] copieGrille = new char[6][7];
//					for (int t=0;t<copieGrille.length;t++) {
//						copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
//					} 
//					
//					Plateau copieJeu = new Plateau(copieGrille, plateau.joueur, plateau.joueurSuivant);
//					
//
//						copieJeu.placerJeton(i, this);
//						
//
//						double valeurDeJeuCourante = minmax( copieJeu,  this, profondeur);
//						if (valeurDeJeuCourante >= valeurDeJeu) {
//							valeurDeJeu = valeurDeJeuCourante;
//							colonneAJouer = i;
//						}
//					}
//			}	
//			}	
//			return colonneAJouer;
//			
//		}
			
		
		@Override
		public int trouverPlacement(Plateau plateau) throws PuissanceException {
			int colonneAJouer;
			if (plateau.isEmpty()) {
				return 3;
			}else {
				colonneAJouer = 1;
			}

			ArrayList<Integer> listCoup = new ArrayList<Integer>();
			for(int i = 0; i < 7; i++){
				if (plateau.isCoupValid(i)) {
					listCoup.add(i);
				}
			}

			int colonneS = plateau.joueur.derniereColonne;
			int ligneS = plateau.getLineValid(colonneS)+1;
			
			char [][] copieGrille = new char[6][7];
			for (int t=0;t<copieGrille.length;t++) {
				copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
			} 
		
			Plateau copieJeu = new Plateau(copieGrille, plateau.joueurSuivant, plateau.joueur);

			if(plateau.chaine_max(ligneS, colonneS, plateau.joueur.getCouleur()) == 3 ) {

				boolean trouveContreAttaque = false;
				int colonne = 0;
				int ligne = copieJeu.getLineValid(colonne);

				while(! trouveContreAttaque && colonne < 7) {
					copieJeu.placerJeton(colonne, copieJeu.joueurSuivant);

					if (copieJeu.chaine_max(ligne, colonne, copieJeu.joueurSuivant.getCouleur()) == 4) {
						trouveContreAttaque = true;
						return colonne;
					}					
					copieJeu.supprimePlacement(colonne);
					colonne++;
					
				}

			}

			Collections.shuffle(listCoup);
		
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i<=7; i++) { // Attention g modif indice 
				if (listCoup.contains(i)) {
					
					
				if(plateau.grilleJeu[0][i-1] == '.'){
					for (int t=0;t<copieGrille.length;t++) {
						copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
					} 
					
						copieJeu.placerJeton(i, this);

						double valeurDeJeuCourante = minmax( copieJeu,  this, profondeur);
						if (valeurDeJeuCourante >= valeurDeJeu) {
							valeurDeJeu = valeurDeJeuCourante;
							colonneAJouer = i;
						}
					}
			}	
			}	
			return colonneAJouer;
			
		}

		
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
				return heuristique.noteGrille(plateau);  
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
				return heuristique.noteGrille(plateau);  
			}
		}

		
		
		public double minmax(Plateau plateau, Joueur joueur, int profondeur) throws PuissanceException {
			return min(plateau, profondeur);
			
		}

		public String toString() {
			return "Le joueur " + this.getNumJoueur();
		}
		
		
}
