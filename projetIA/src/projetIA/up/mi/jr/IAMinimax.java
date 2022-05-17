package projetIA.up.mi.jr;

import java.util.ArrayList;



/**
 * Represente une IA qui implémente MiniMax
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class IAMinimax extends Joueur{
	

	/**
	 * Représente la profondeur d'évaluation	
	 */
	private int profondeur;

	/**
	 * Représente un objet de type Heuristique	
	 */
	private Heuristique heuristique;
	
	/**
	 * Constructeur d'une IA MiniMax
	 * @param couleurJeton représente la couleur du jeton du joueur
	 * @param numJoueur représente le numéro de passage du joueur
	 * @param profondeur représente la profondeur d'évaluation
	 * @param niveau représente le niveau de l'IA
	 */
	public IAMinimax(char couleurJeton, int numJoueur, int profondeur) {
		super(couleurJeton, numJoueur);
		this.profondeur = profondeur;
		heuristique = new Heuristique();
		
	}
	
	/**
	 * Renvoie le numéro de la colonne dans laquelle le jeton est à placer dans la grille 
	 */
	@Override
	public int trouverPlacement(Plateau plateau) throws PuissanceException {
		//pas obligé si on joue sur la 7 il trouve le milieu
		
//			if (plateau.estVide()) {
//				return 3;
//			}
			
			
			// On cherche la liste des coups valides
			ArrayList<Integer> listeCoupValide = new ArrayList<Integer>();
			
			
			for(int i = 0; i <= 6; i++){
					if(plateau.getLigneValide(i) != -1){
						listeCoupValide.add(i);
					}
				} 
			
			double valeurDeJeu = heuristique.getMinScore();
			
			ArrayList<Integer> colonnesAJouer = new ArrayList<Integer>();
			
			// on parcours la liste pour choisir un coup parmi ceux valide
			for(int i : listeCoupValide) {
				
				//DEEP COPY
				Plateau copieJeu = plateau.copieGrille();
				
				//Contre attaque
//				if(plateau.aGagne(plateau.joueurSuivant, 3 )) {
//					boolean trouveContreAttaque = false;
//					int colonne = 0;
//					while(! trouveContreAttaque && colonne < 7) {
//						copieJeu.placerJeton(colonne, copieJeu.joueurSuivant);
//						if (copieJeu.aGagne(copieJeu.joueurSuivant, 4)) {
//
//							trouveContreAttaque = true;
//							return colonne;
//						}					
//						copieJeu.supprimePlacement(colonne);
//						colonne++;
//					}
//				}
					
				// on cherche le coup qui maximise minmax	
				
				
					copieJeu.placerJeton(i, this);
					double valeurDeJeuCourante = minmax(copieJeu);
					if (valeurDeJeuCourante == valeurDeJeu){
						colonnesAJouer.add(i);
					}else if(valeurDeJeuCourante > valeurDeJeu){
						colonnesAJouer.clear();
						valeurDeJeu = valeurDeJeuCourante;
						colonnesAJouer.add(i);
					}	
					
				}
//			return colonneAJouer;
			
			//Choisir au hasard parmi les coups 
			int numeroDeColonneAJouer = (int) (Math.random() * colonnesAJouer.size());
			return colonnesAJouer.get(numeroDeColonneAJouer);
		}
	
	
	
	
	
	/**
	 * Renvoie le résultat de Min de l'algorithme MiniMax
	 * @param plateau représente le plateau de jeu courant
	 * @param profondeur représente la profondeur d'évaluation
	 * @return le résultat de Min de l'algorithme MiniMax
	 */
	public double min(Plateau plateau, int profondeur) throws PuissanceException {
		
//		if(plateau.aGagne(plateau.getJoueurCourant(), 4)){
//			System.out.println("**********");
//			plateau.affichePlateau();
//			System.out.println("**********");
//			System.out.println("Joueur courant : " + plateau.getJoueurCourant());
//			System.out.println("evaluation : "+ heuristique.getMaxScore() );
//			return heuristique.getMaxScore();
//		}

		if(profondeur != 0){			
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){ 
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.getJoueurCourant()); //Ici changement jCourant à jSuivant ??
						}
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, profondeur-1));
					}
			
			
					}
			return valeurDeJeu;
		}else{
			//return Math.abs(heuristique.evaluation(plateau));
			return heuristique.evaluation(plateau); 
			

		}
	}
	
	/**
	 * Renvoie le résultat de Max de l'algorithme MiniMax
	 * @param plateau représente le plateau de jeu courant
	 * @param profondeur représente la profondeur d'évaluation
	 * @return le résultat de Max de l'algorithme MiniMax
	 */
	public double max(Plateau plateau, int profondeur) throws PuissanceException {
		
//		if(plateau.aGagne(plateau.getJoueurSuivant(), 4)){
//			System.out.println("**********");
//			plateau.affichePlateau();
//			System.out.println("**********");
//			System.out.println("Joueur courant : " + plateau.getJoueurCourant());
//			System.out.println("evaluation : "+ heuristique.getMinScore() );
//		return heuristique.getMinScore();
//	}
		
		if(profondeur != 0){
			
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.getJoueurCourant());
						}
						valeurDeJeu = Math.max(valeurDeJeu, this.min(copieJeu,  profondeur-1));
					}
			
			}
			return valeurDeJeu;
		}else{
			//return Math.abs(heuristique.evaluation(plateau));
			return heuristique.evaluation(plateau);  
			
		}
	}

	/**
	 * Renvoie le résultat de l'algorithme MiniMax
	 * @param plateau représente le plateau de jeu courant
	 * @return le résultat de l'algorithme MiniMax
	 * @throws PuissanceException
	 */
	public double minmax(Plateau plateau) throws PuissanceException {
		return min(plateau, profondeur);
		
	}
	
	/**
	 * Redéfinition de la méthode toString
	 */
	@Override
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}
	
	
	
	// NEW VERSION 
//	@Override
//	public int trouverPlacement(Plateau plateau) throws PuissanceException {
//		int colonneAJouer = 1;
//		double valeurDeJeu = heuristique.getMinScore();
//		for(int i=1; i < 6; i++){
//				if(plateau.grilleJeu[0][i] == '.'){
//					Plateau copieJeu = plateau.copieGrille();
//					copieJeu.placerJeton(i, copieJeu.getJoueurCourant());
//					double valeurDeJeuCourante = minmax(copieJeu, profondeur);
//					if (valeurDeJeuCourante >= valeurDeJeu){
//						valeurDeJeu = valeurDeJeuCourante;
//						colonneAJouer = i;
//					}
//				}
//
//		}
//		return colonneAJouer;
//	}
//	
//	public double minmax(Plateau plateau, int profondeur) throws PuissanceException {
//		return min(plateau, profondeur);
//		
//	}
//	
//	public double min(Plateau plateau, int profondeur) throws PuissanceException {
//			if(profondeur != 0){			
//				double valeurDeJeu = heuristique.getMaxScore();
//				for(int i=1; i <=6; i++){
//					
//						if(plateau.grilleJeu[0][i-1] == '.'){ 
//							//DEEP COPY
//							Plateau copieJeu = plateau.copieGrille();
//							if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
//								copieJeu.placerJeton(i, copieJeu.getJoueurSuivant());
//							}
//							valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, profondeur-1));
//						}
//				
//				
//						}
//				return valeurDeJeu;
//			}else{
//				return heuristique.evaluation(plateau); 
//				
//	
//			}
//		}
//	
//	public double max(Plateau plateau, int profondeur) throws PuissanceException {
//			if(profondeur != 0){
//				
//				double valeurDeJeu = heuristique.getMinScore();
//				for(int i=1; i <= 6; i++){
//					
//						if(plateau.grilleJeu[0][i-1] == '.'){
//							//DEEP COPY
//							Plateau copieJeu = plateau.copieGrille();
//							if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
//								copieJeu.placerJeton(i, copieJeu.getJoueurCourant());
//							}
//							valeurDeJeu = Math.max(valeurDeJeu, this.min(copieJeu,  profondeur-1));
//						}
//				
//				}
//				return valeurDeJeu;
//			}else{
//				return heuristique.evaluation(plateau);  
//				
//			}
//		}
//
}