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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [][] grilleJeu = new char [6][7];
		for(int j=0; j<6; j++) {
			for(int i = 0; i<7; i++) {
				grilleJeu[j][i] = '.';
			}
		}
		affichePlateau(grilleJeu);	
		grilleJeu[1][0] = 'J';
		grilleJeu[2][0] = 'J';
		grilleJeu[3][0] = 'J';
		grilleJeu[4][0] = 'J';
		grilleJeu[4][1] = 'R';
		grilleJeu[5][0] = 'R';
		grilleJeu[5][1] = 'R';
		System.out.println("***********");
		affichePlateau(grilleJeu);
		
		Plateau plateau = new Plateau(grilleJeu);
		System.out.println(plateau.resultatAlignementDeJeton('J', 1 ,0, 0,1));
		System.out.println(plateau.chercheAlignementDeJeton('J',2, 1, 0, 1));
	}
	
	

}


