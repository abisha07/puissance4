package projetIA.up.mi.jr;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represente une IA qui implémente AlphaBeta
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class IAAlphaBeta extends Joueur{

	/**
	 * Représente la profondeur d'évaluation	
	 */
	private int profondeur;
	
	/**
	 * Représente un objet de type Heuristique	
	 */
	private Heuristique heuristique;
	
	/**
	 * Représente le niveau de l'IA
	 */
	private int niveau;
	
	//TODO //A enlever
	private int coup;
	
	/**
	 * Constructeur d'une IA AlphaBeta
	 * @param couleurJeton représente la couleur du jeton du joueur
	 * @param numJoueur représente le numéro de passage du joueur
	 * @param profondeur représente la profondeur d'évaluation
	 * @param niveau représente le niveau de l'IA
	 */
	public IAAlphaBeta(char couleurJeton, int numJoueur,int profondeur, int niveau) {
		super(couleurJeton, numJoueur);
		this.profondeur = profondeur;
		this.niveau = niveau;
		heuristique = new Heuristique();
	}
	
	/**
	 * Renvoie le numéro de la colonne dans laquelle le jeton est à placer dans la grille 
	 */
	@Override
	public int trouverPlacement(Plateau plateau) {
		int colonneAJouer = 1;
		if (plateau.estVide()) {
			return 3;
		}
		ArrayList<Integer> colonnesAJouer = new ArrayList<Integer>();
		
		// On initialise les résultat avec la première colonne jouable pour éviter
		// que l'IA ne selectionne une colonne non jouable par défaut
		for(int i = 1; i <= 6; i++){
				if(plateau.grilleJeu[0][i -1] == '.'){
					colonnesAJouer.add(i);
					break;
				}
			} 

		double valeurDeJeu = heuristique.getMinScore();
		for(int i=1; i <= 6; i++){
			try {
				if(plateau.grilleJeu[0][i -1] == '.'){
					//DEEP COPY
					Plateau copieJeu = plateau.copieGrille();
					if(plateau.aGagne(plateau.joueur, 3 )) {
						
						boolean trouveContreAttaque = false;
						int colonne = 0;
						while(! trouveContreAttaque && colonne < 7) {
							copieJeu.placerJeton(colonne, copieJeu.joueurSuivant);
							if (copieJeu.aGagne(copieJeu.joueurSuivant, 4)) {
								trouveContreAttaque = true;
								return colonne;
							}					
							copieJeu.supprimePlacement(colonne);
							colonne++;
						}
					}
							
					copieJeu.placerJeton(i, this);
					
					double valeurDeJeuCourante = alphabeta(copieJeu, this);
					if (valeurDeJeuCourante == valeurDeJeu){
						colonnesAJouer.add(i);
					}else if(valeurDeJeuCourante > valeurDeJeu){
						colonnesAJouer.clear();
						valeurDeJeu = valeurDeJeuCourante;
						colonnesAJouer.add(i);
					}
				}
			} catch (PuissanceException e) {
				e.printStackTrace();
			}
		}
		
		int numeroDeColonneAJouer = (int) (Math.random() * colonnesAJouer.size());
		return colonnesAJouer.get(numeroDeColonneAJouer);
	
	}
	
	/**
	 * Renvoie le résultat de l'algorithme AlphaBeta
	 * @param plateau représente le plateau de jeu courant
	 * @param joueur représente le joueur courant
	 * @return le résultat de l'algorithme AlphaBeta
	 */
	private double alphabeta(Plateau plateau, Joueur joueur){
		double alpha = heuristique.getMinScore();
		double beta= heuristique.getMaxScore();
		return this.min(plateau, joueur, profondeur, alpha, beta);
	}
	
	/**
	 * Renvoie le résultat de Min de l'algorithme AlphaBeta
	 * @param plateau représente le plateau de jeu courant
	 * @param joueur représente le joueur courant
	 * @param profondeur représente la profondeur d'évaluation
	 * @param alpha représente la valeur de alpha
	 * @param beta représente la valeur de beta
	 * @return le résultat de Min de l'algorithme AlphaBeta
	 */
	private double min(Plateau plateau, Joueur joueur,int profondeur, double alpha, double beta){
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6 ; i++){
				try {
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.joueur);
						}
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, joueur, profondeur-1, alpha, beta));
						
						if(alpha >= valeurDeJeu){
							return valeurDeJeu; // Coupure alpha
						}
						
		               beta = Math.min(beta, valeurDeJeu);
						
					}
				} catch (PuissanceException e) {
					e.printStackTrace();
				}
			}
			return valeurDeJeu;
		}else{
			if(this.niveau == 1) {
				return this.heuristique.randomHeuristique();
			}else {
				return this.heuristique.evaluation(plateau);
			}
			
		}
	}
	
	/**
	 * Renvoie le résultat de Max de l'algorithme AlphaBeta
	 * @param plateau représente le plateau de jeu courant
	 * @param joueur représente le joueur courant
	 * @param profondeur représente la profondeur d'évaluation
	 * @param alpha représente la valeur de alpha
	 * @param beta représente la valeur de beta
	 * @return le résultat de Max de l'algorithme AlphaBeta
	 */
	private double max(Plateau plateau, Joueur joueur, int profondeur, double alpha, double beta){
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				try{
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.joueur);
						}
						valeurDeJeu = Math.max(valeurDeJeu, this.min(copieJeu, joueur, profondeur-1, alpha, beta));
		
						if(valeurDeJeu >= beta){
							return valeurDeJeu; // Coupure beta
						}
						alpha = Math.max(alpha	, valeurDeJeu);
						
					}
				} catch (PuissanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return valeurDeJeu;
		}else{
			if(this.niveau == 1) {
				return this.heuristique.randomHeuristique();
			}else {
				return this.heuristique.evaluation(plateau);
			}
		}
	
	}
	
	
	
	//TODO A enlever
//	private int ab_min(Plateau plateau, int alpha, int beta, int depth) throws PuissanceException {
//			
//			// p_courant.joueur == -1
//			if (plateau.aGagne(plateau.joueur, 4)) {
//				return Integer.MAX_VALUE;
//			} else if (depth == 0) {
//				return heuristique.evaluation(plateau);
//			}
//			
//			// p_child servira comme support pour tester tout les coups possibles
//			char [][] copieGrille = new char[6][7];
//			for (int t=0;t<copieGrille.length;t++) {
//				copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
//			} 
//			Plateau copieJeu = new Plateau(copieGrille, plateau.joueur, plateau.joueurSuivant);
//			// Creation de la liste des coups possibles
//			ArrayList<Integer> listCoup = new ArrayList<Integer>();
//			for(int i = 0; i < 7; i++){
//				if (copieJeu.isCoupValid(i)) {
//					listCoup.add(i);
//				}
//			}
//			int value;
//			
//			// Boucle de parcourt de tout les enfants
//			for (int coup : listCoup) {
//				copieJeu.placerJeton(coup, copieJeu.joueur);
//				
//				value = ab_max(copieJeu, alpha, beta, depth-1);
//				
//				copieJeu.supprimePlacement(coup);
//				//copieJeu.getBack(line, coup);
//				
//				if (value < beta) {
//					beta = value;
//					if (alpha > beta) {
//						return beta;
//					}
//				}
//			}
//			
//			return beta;
//		}
//		
//		/** Algorithme Alpha Beta MaxMin 
//		 * @throws PuissanceException */
//		private int ab_max(Plateau p_courant, int alpha, int beta, int depth) throws PuissanceException {
//			
//			// p_courant.joueur == 1
//			if (p_courant.hasWon(p_courant.joueur, 4)) {
//				return Integer.MIN_VALUE;
//			} else if (depth == 0) {
//				return heuristique.evaluation(p_courant);
//			}
//			
//			// p_child servira comme support pour tester tout les coups possibles
//			char [][] copieGrille = new char[6][7];
//			for (int t=0;t<copieGrille.length;t++) {
//				copieGrille[t] = Arrays.copyOf(p_courant.grilleJeu[t], p_courant.grilleJeu[t].length);
//			} 
//			Plateau p_child = new Plateau(copieGrille, p_courant.joueur, p_courant.joueurSuivant);
//			// Creation de la liste des coups possibles
//			ArrayList<Integer> listCoup = new ArrayList<Integer>();
//			for(int i = 0; i < 7; i++){
//				if (p_child.isCoupValid(i)) {
//					listCoup.add(i);
//				}
//			}
//			int value;
//			
//			for (int coup : listCoup) {
//				p_child.placerJeton(coup, p_child.joueur);
//				
//				value = ab_min(p_child, alpha, beta, depth-1);
//				
//				p_child.supprimePlacement(coup);
//				
//				if (value > alpha) {
//					alpha = value;
//					if (alpha > beta) {
//						return alpha;
//					}
//				}
//			}
//			
//			return alpha;
//		}
//		
//		
//		private void jouerAlphaBeta(Plateau p, int level) throws PuissanceException {
//			int value, value_min = Integer.MAX_VALUE;
//			
//			ArrayList<Integer> listCoup = new ArrayList<Integer>();
//			for(int i = 0; i < 7; i++){
//				if (p.isCoupValid(i)) {
//					listCoup.add(i);
//				}
//			}
//			
//			for (int coup : listCoup) {
//				int line = p.placerJeton(coup, p.joueurSuivant);
//				
//				value = ab_max(p, Integer.MIN_VALUE, Integer.MAX_VALUE, level-1);
//				
//				p.supprimePlacement(coup);
//				
//				if (value <= value_min) {
//					value_min = value;
//					this.coup = coup;
//				}
//			}
//		}
//		
//		/** Meilleur coup des blancs 
//		 * @throws PuissanceException */
//		private void jouerBetaAlpha(Plateau p, int level) throws PuissanceException {
//			int value, value_max = Integer.MIN_VALUE;
//			
//			ArrayList<Integer> listCoup = new ArrayList<Integer>();
//			for(int i = 0; i < 7; i++){
//				if (p.isCoupValid(i)) {
//					listCoup.add(i);
//				}
//			}
//			
//			for (int coup : listCoup) {
//				int line = p.placerJeton(coup, p.joueur);
//				
//				value = ab_min(p, Integer.MIN_VALUE, Integer.MAX_VALUE, level-1);
//				
//				p.supprimePlacement(coup);
//				
//				if (value >= value_max) {
//					value_max = value;
//					this.coup = coup;
//				}
//			}
//		}
//
//		
}
