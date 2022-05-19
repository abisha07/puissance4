package projetIA.up.mi.jr;

import java.util.ArrayList;

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
	 * @throws PuissanceException 
	 */
	@Override
	public int trouverPlacement(Plateau plateau) throws PuissanceException {
		
		// On cherche la liste des coups valides
		ArrayList<Integer> listeCoupValide = new ArrayList<Integer>();
		
		
		for(int i = 0; i <= 6; i++){
				if(plateau.getLigneValide(i) != -1){
					listeCoupValide.add(i);
				}
			} 
		
		double valeurDeJeu = heuristique.getMinScore();
		
		ArrayList<Integer> colonnesAJouer = new ArrayList<Integer>();
		
		if(plateau.getCouleur(5, 3) == '.') {
            return 3;
        }else if(plateau.getNbTour() == 2) {
            return 4;
        }
		
		for(int i : listeCoupValide) {
			
			//DEEP COPY
			Plateau copieJeu = plateau.copieGrille();
			
			double valeurDeJeuCourante = alphabeta(copieJeu, this);
			if( heuristique.coupGagnant(copieJeu, i) || heuristique.coupPerdant(copieJeu, i)) {
				if(heuristique.coupGagnant(copieJeu, i)) {
					valeurDeJeuCourante = heuristique.getMaxScore();
					return i;
				}
				if(heuristique.coupPerdant(copieJeu, i)) {
					valeurDeJeuCourante = heuristique.getMaxScore();
					return i;
				}
				
			}else {
				copieJeu.placerJeton(i, this);
				valeurDeJeuCourante = alphabeta(copieJeu, this);
			}
			
			
			if (valeurDeJeuCourante == valeurDeJeu){
				colonnesAJouer.add(i);
			}else if(valeurDeJeuCourante > valeurDeJeu){
				colonnesAJouer.clear();
				valeurDeJeu = valeurDeJeuCourante;
				colonnesAJouer.add(i);
			}
		}
		
		
		//Choisir au hasard parmi les coups 
		int numeroDeColonneAJouer = (int) (Math.random() * colonnesAJouer.size());
		return colonnesAJouer.get(numeroDeColonneAJouer);
	
	}
	
	/**
	 * Renvoie le résultat de l'algorithme AlphaBeta
	 * @param plateau représente le plateau de jeu courant
	 * @param joueur représente le joueur courant
	 * @return le résultat de l'algorithme AlphaBeta
	 * @throws PuissanceException 
	 */
	private double alphabeta(Plateau plateau, Joueur joueur) throws PuissanceException{
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
	 * @throws PuissanceException 
	 */
	private double min(Plateau plateau, Joueur joueur,int profondeur, double alpha, double beta) throws PuissanceException{
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6 ; i++){
				try {
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.getJoueurCourant());
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
	 * @throws PuissanceException 
	 */
	private double max(Plateau plateau, Joueur joueur, int profondeur, double alpha, double beta) throws PuissanceException{
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				try{
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.getJoueurCourant());
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
	
	/**
	 * Redéfinition de la méthode toString
	 */
	@Override
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}
	
}
