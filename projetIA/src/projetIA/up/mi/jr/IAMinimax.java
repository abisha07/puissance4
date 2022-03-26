package projetIA.up.mi.jr;

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
				
				Plateau copieJeu = new Plateau(copieGrille);
				try {
					//System.out.println("GGGGG");
					copieJeu.placerJeton(i, this);
					//System.out.println("LLLLL");
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
	
	public double min(Plateau plateau, Joueur joueur, int profondeur) throws PuissanceException {

		if(profondeur != 0){
			
			double valeurDeJeu = heuristique.getMaxScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
							
						Plateau copieJeu = new Plateau(copieGrille);
						copieJeu.placerJeton(i, joueur);
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieJeu, joueur, profondeur-1));
					}
			
			}
			return valeurDeJeu;
		}else{
			return heuristique.randomHeuristique();  
		}
	}

	
	public double max(Plateau plateau, Joueur joueur, int profondeur) throws PuissanceException {
		
		if(profondeur != 0){
			
			double valeurDeJeu = heuristique.getMinScore();
			for(int i=1; i <= 6; i++){
				
					if(plateau.grilleJeu[0][i -1] == '.'){
						//DEEP COPY
						char [][] copieGrille = new char[6][7];
						for (int t=0;t<copieGrille.length;t++) {
							copieGrille[t] = Arrays.copyOf(plateau.grilleJeu[t], plateau.grilleJeu[t].length);
						} 
							
						Plateau copieJeu = new Plateau(copieGrille);
						copieJeu.placerJeton(i, joueur);
						valeurDeJeu = Math.max(valeurDeJeu, this.min(copieJeu, joueur, profondeur-1));
					}
			
			}
			return valeurDeJeu;
		}else{
			return heuristique.randomHeuristique();  
		}
	}
	
	public double minmax(Plateau plateau, Joueur joueur, int profondeur) throws PuissanceException {
		return min(plateau, joueur, profondeur);
		
	}
	
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}
}
