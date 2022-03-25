package projetIA.up.mi.jr;

import java.util.ArrayList;
/**
 * Représente un type de joueur (humain ou IA)
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public abstract class Joueur {
	/**
	 * Un joueur est défini par une couleur pour ses jetons
	 */
	private char couleur;
	
	private int numJoueur;
	
	
	public Joueur(char couleur, int numJoueur) {
		this.couleur=couleur;
		this.numJoueur = numJoueur;

	}
	/**
	 * Getteur renvoyant la couleur des jetons du joueur 
	 * @return couleur des jetons du joueur 
	 */
	public char getCouleur() {
		return couleur;
	}
	
	
	public int getNumJoueur() {
		return numJoueur;
	}

	
	
	
	/**
	 * Renvoie sous forme de liste d'Integer les coordonnées du jeton à placer dans la grille 
	 * @return liste d'Interger représentant les coordonnées du jeton à placer 
	 */
	public abstract int trouverPlacement(Plateau plateau);

	
	
	
	
}
