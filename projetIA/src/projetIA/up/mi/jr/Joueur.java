package projetIA.up.mi.jr;

import java.util.ArrayList;
/**
 * Représente un type de joueur (humain ou IA)
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public interface Joueur {
	/**
	 * Un joueur est défini par une couleur pour ses jetons
	 */
	char couleur=0;
	
	/**
	 * Getteur renvoyant la couleur des jetons du joueur 
	 * @return couleur des jetons du joueur 
	 */
	public char getCouleur();
	
	/**
	 * Renvoie sous forme de liste d'Integer les coordonnées du jeton à placer dans la grille 
	 * @return liste d'Interger représentant les coordonnées du jeton à placer 
	 */
	public ArrayList<Integer> trouverPlacement();
	
	
	
}
