package projetIA.up.mi.jr;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe implémentant l'interface Joueur pour représenter un joueur humain
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class JoueurHumain implements Joueur{
	/**
	 * Objet de type Plateau représentant le plateau de jeu du Puissance 4
	 */
	private Plateau plateau;
	/**
	 * La couleur des jetons du joueur
	 */
	private char couleurJeton;
	
	private int numeroJoueur;
	
	/**
	 * Constructeur à partir de la couleur des jetons du joueur 
	 * @param couleurJeton Couleur des jetons du joueur 
	 */
	public JoueurHumain(char couleurJeton, int numeroJoueur) {
		this.couleurJeton = couleurJeton;
		plateau = Plateau.getInstance();
		this.numeroJoueur = numeroJoueur;
	}
	
	/**
	 * Redéfinition du getteur renvoyant la couleur des jetons du joueur 
	 */
	public char getCouleur() {
		return couleurJeton;
	}

	/**
	 * Redéfinition de la méthode trouverPlacement 
	 * qui renvoie sous forme de liste d'Integer les coordonnées du jeton à placer dans la grille 
	 */
	@Override
	public ArrayList<Integer> trouverPlacement() {
		System.out.println("Entrez le numéro de la colonne entre 1 et 7");
		boolean place = false; //boolean verifiant si le jeton du joueur a été placé
		ArrayList<Integer> coordonnee = new ArrayList<>();
		try {
			Scanner sc = new Scanner(System.in);
			int col = sc.nextInt(); //numéro de la colonne saisi par l'utilisateur via clavier
			while(!place) {
			if(col >= 1 && col <=7) { // verifie que le numero de la  colonne est bien comprise entre 1 et 7
				
				// indice [col-1][5] correspond à la dernière case partant du bas de la colonne col
				// on verifie que cette case est bien libre
				if (plateau.grilleJeu[5][col-1] != '.'){ 
					System.out.println("Colonne pleine, choisir une autre colonne");
				}else {
					place = true;
					int rang = 0;
					while(plateau.grilleJeu[rang][col-1] != '.' && rang < 6) { // pour placer le jeton au-dessus des autres jetons de la colonne col
						rang ++;
					}
					coordonnee.add(col);
					coordonnee.add(rang);
				}
			}else {
				System.out.println("Numéro de la colonne incorrect, réitérez");
			}
		sc.close();
		}
	}catch(Exception e) {
		System.out.println("Numéro de la colonne incorrect, réitérez");
	}
	return coordonnee;
	}	
		
	public String toString() {
		return "Le joueur " + numeroJoueur;
	}
}
