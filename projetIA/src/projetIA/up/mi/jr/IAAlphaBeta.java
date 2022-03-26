package projetIA.up.mi.jr;

import java.util.ArrayList;
import java.util.Arrays;

public class IAAlphaBeta extends Joueur{
	//private int niveau = 2;

		private int profondeur;
		private Heuristique heuristique;
	
	public IAAlphaBeta(char couleurJeton, int numJoueur,int profondeur) {
		super(couleurJeton, numJoueur);
		this.profondeur = profondeur;
		heuristique = new Heuristique();
	}
	

	@Override
	public int trouverPlacement(Plateau plateau) {
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
					char [][] copieGrille = new char[6][7];
					for (int t=0;t<copieGrille.length;t++) {
						copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
					} 
					Plateau copieJeu = new Plateau(copieGrille);
					copieJeu.placerJeton(i, this);
					
					double valeurDeJeuCourante = alphabeta(copieJeu, this, profondeur);
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
	
	
	private double alphabeta(Plateau plateau, Joueur joueur, int profondeur){
		double alpha = heuristique.getMinScore();
		double beta= heuristique.getMaxScore();
		return this.min(plateau, joueur, profondeur, alpha, beta);
	}
	
	private double min(Plateau plateau, Joueur joueur,  int profondeur, double alpha, double beta){
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6 ; i++){
				try {
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
						Plateau copieJeu = new Plateau(copieGrille);
						copieJeu.placerJeton(i, this);
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
			return this.heuristique.randomHeuristique();
		}
	}
	
	private double max(Plateau plateau, Joueur joueur, int profondeur, double alpha, double beta){
		if(profondeur != 0){
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				try{
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
						Plateau copieJeu = new Plateau(copieGrille);
						copieJeu.placerJeton(i, this);
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
			return this.heuristique.randomHeuristique();
		}
	
	}
}
