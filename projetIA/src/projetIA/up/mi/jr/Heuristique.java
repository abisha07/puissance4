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
	
	public double noteGrille2(Plateau plateau,  Joueur joueur) {
			
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
	
	public double noteGrille(Plateau plateau) throws PuissanceException {
			int colonne = plateau.joueur.derniereColonne;
			int ligne = plateau.getLineValid(colonne)+1;
			
			int colonneS = plateau.joueurSuivant.derniereColonne;
			int ligneS = plateau.getLineValid(colonneS)+1;
//			char couleurJoueurSuivant;
//			if (joueur.getCouleur() == 'R') {
//				couleurJoueurSuivant = 'J';
//			}else {
//				couleurJoueurSuivant = 'R';
//			}
			
			if(plateau.chaine_max(ligneS, colonneS, plateau.joueurSuivant.getCouleur()) == 4){
				return MIN_SCORE;
			}
			
			if(plateau.chaine_max(ligne, colonne, plateau.joueur.getCouleur()) == 4){
				return MAX_SCORE;
			}
			
			//int score = plateau.chaine_max(couleurJoueurSuivant, couleurJoueurSuivant)
			
			
			int score = plateau.chaine_max(ligne, colonne, plateau.joueur.getCouleur());
			int score2Jetons = 0;
			int score3Jetons = 0;
			int score4Jetons = 0;
			switch (score) {
			case 2:	
				score2Jetons = score;
				
				break;
				
			case 3:
				score3Jetons = 3*score;
				break;
			
			case 4:
				score4Jetons = 9*score;
				break;
			}			
			return score2Jetons + score3Jetons + score4Jetons;
		}

	

}
