package projetIA.up.mi.jr;

public class Heuristique {
	
	private int MAX_SCORE;
	private int MIN_SCORE;
	
	public Heuristique() {
		MAX_SCORE = Integer.MAX_VALUE;
		MIN_SCORE = Integer.MIN_VALUE;
	}
	
	public double randomHeuristique() {
		return Math.random();
	}
	
	public int getMaxScore() {
		return MAX_SCORE;
	}
	
	public int getMinScore() {
		return MIN_SCORE;
	}
	
	public double noteGrille(Plateau plateau,  Joueur joueur) {
			
			char couleurJoueurSuivant;
			if (joueur.getCouleur() == 'R') {
				couleurJoueurSuivant = 'J';
			}else {
				couleurJoueurSuivant = 'R';
			}
			
	
			if(plateau.chercheAlignement(couleurJoueurSuivant)){
				System.out.println("arrive MinScore");
				return MIN_SCORE;
			}
	
			if(plateau.chercheAlignement(joueur.getCouleur())){
				System.out.println("arrive MaxScore");
				return MAX_SCORE;
			}
			
			double resultat = 0;
			
			for(int i = 1; i <= 6; i++){
				for(int j = 1; j <= 7; j++){
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j,0, 1) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, 1, 1) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, 1, 0) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, 1, -1) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, 0, -1) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, -1, -1) ;
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j, -1, 0) ; 
					resultat +=plateau.resultatAlignementDeJeton(joueur.getCouleur(), i, j,-1, 1) ;
					
				}
			}
	
			return resultat;
		}
	

}
