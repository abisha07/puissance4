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
	//@Override
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
			
			// on parcours la liste pour choisir un coup parmi ceux valide
			for(int i : listeCoupValide) {
				
				//DEEP COPY
				Plateau copieJeu = plateau.copieGrille();
				
				
					if (heuristique.coupGagnant(plateau) != 0) {
						//valeurDeJeuCourante = heuristique.getMaxScore();
						return heuristique.coupGagnant(plateau);
						
						
					}if(heuristique.coupPerdant(plateau) != 0) {
						//valeurDeJeuCourante = heuristique.getMaxScore();
						return heuristique.coupPerdant(plateau);
					}
						//valeurDeJeuCourante = heuristique.getMaxScore();

					copieJeu.placerJeton(i, this);
			
					//double valeurDeJeuCourante;
					
					
					double valeurDeJeuCourante = minmax(copieJeu, profondeur);
					
					
					
					if (valeurDeJeuCourante == valeurDeJeu){
						colonnesAJouer.add(i);
					}else if(valeurDeJeuCourante >= valeurDeJeu){
						
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
	 * Renvoie le résultat de Min de l'algorithme MiniMax
	 * @param plateau représente le plateau de jeu courant
	 * @param profondeur représente la profondeur d'évaluation
	 * @return le résultat de Min de l'algorithme MiniMax
	 */
	public double min(Plateau plateau, int profondeur) throws PuissanceException {
		if(profondeur != 0){			
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){ 
						//DEEP COPY
						Plateau copieJeu = plateau.copieGrille();
						if (copieJeu.estCoupValide(i) && copieJeu.getLigneValide(i) !=-1) {
							copieJeu.placerJeton(i, copieJeu.getJoueurCourant()); 

						}
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, profondeur-1));
					}
			
			
					}
			return valeurDeJeu;
		}else{
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
//			if(heuristique.coupGagnant(plateau) || heuristique.coupPerdant(plateau)) {
//				return heuristique.getMaxScore();
//			}
			return heuristique.evaluation(plateau);  
			
		}
	}

	/**
	 * Renvoie le résultat de l'algorithme MiniMax
	 * @param plateau représente le plateau de jeu courant
	 * @return le résultat de l'algorithme MiniMax
	 * @throws PuissanceException
	 */
	public double minmax(Plateau plateau, int profondeur) throws PuissanceException {
		return min(plateau, profondeur);
		
	}
	
	/**
	 * Redéfinition de la méthode toString
	 */
	@Override
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}

}