package projetIA.up.mi.jr;


import java.util.Scanner;

/**
 * Classe implémentant l'interface Joueur pour représenter un joueur humain
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class JoueurHumain extends Joueur{
	
	/**
	 * Constructeur à partir de la couleur des jetons du joueur 
	 * @param couleurJeton Couleur des jetons du joueur 
	 * @param numeroJeton Numéro de passage du joueur
	 */
	public JoueurHumain(char couleurJeton, int numeroJoueur) {
		super(couleurJeton,numeroJoueur);
	}
	

	/**
	 * Renvoie le numéro de la colonne dans laquelle le jeton est à placer dans la grille 
	 * @param plateau Représente le plateau de jeu courant
	 * @throws PuissanceException 
	 */
	@Override
	public int trouverPlacement(Plateau plateau) throws PuissanceException {
		boolean place = false; //boolean verifiant si le jeton du joueur a été placé
		int colonnePlacer = 0;
		while(!place) {
			System.out.println("Entrez le numéro de la colonne entre 0 et 6");
			Scanner sc = new Scanner(System.in);
			int col = sc.nextInt(); //numéro de la colonne saisi par l'utilisateur via clavier
			
			if (plateau.estCoupValide(col)) {
				place = true;
				sc.close();
				colonnePlacer =col;		
			}
		}
		return colonnePlacer;
	}
	
	/**
	 * Redéfinition de la méthode toString
	 */
	@Override
	public String toString() {
		return "Le joueur " + this.getNumJoueur();
	}

}
