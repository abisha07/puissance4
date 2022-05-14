package projetIA.up.mi.jr;

public class TestPuissance4 {

	public static void affichePlateau(char grilleJeu [][]) {
		
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				System.out.print(" | " + grilleJeu[i][j]);
			}
			System.out.println(" | ");
		}
		//System.out.println();
	}
	
	public static void main(String[] args) throws PuissanceException {
		// TODO Auto-generated method stub
		char [][] grilleJeu = new char [6][7];
		for(int j=0; j<6; j++) {
			for(int i = 0; i<7; i++) {
				grilleJeu[j][i] = '.';
			}
		}
//		affichePlateau(grilleJeu);	
//		grilleJeu[1][0] = 'J';
//		grilleJeu[2][0] = 'J';
//		grilleJeu[3][0] = 'J';
//		grilleJeu[4][0] = 'J';
//		grilleJeu[4][1] = 'R';
//		grilleJeu[5][0] = 'R';
//		grilleJeu[5][1] = 'R';
//		System.out.println("***********");
//		affichePlateau(grilleJeu);
//		System.out.println("***********");
	JoueurHumain joueur1 = new JoueurHumain('J', 1);
	JoueurHumain joueur2 = new JoueurHumain('R', 2);
	Plateau plateau = new Plateau(joueur1, joueur2, 1);
	plateau.affichePlateau();
	Heuristique h = new Heuristique();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(5, 0,'J');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(4, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(3, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	plateau.setCouleur(2, 0,'R');
	plateau.affichePlateau();
	System.out.println(h.evaluation(plateau));
	
	
//		int ligne = plateau.getLigneValide(0);
//		//System.out.println(ligne+1);
//		System.out.println(grilleJeu[ligne+1][0]);
//		 
//		plateau.supprimePlacement(0);
//		
//		System.out.println(grilleJeu[ligne+1][0]);
//		plateau.affichePlateau();
//		System.out.println(plateau.estVide());
//		//System.out.println(plateau.resultatAlignementDeJeton('J', 1 ,0, 0,1));
//		//System.out.println(plateau.chercheAlignementDeJeton('J',2, 1, 0, 1));
		
		
		
		
	}
	
	

}


