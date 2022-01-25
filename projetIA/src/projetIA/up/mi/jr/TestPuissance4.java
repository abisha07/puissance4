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
	}

}


