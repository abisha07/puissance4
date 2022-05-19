package projetIA.up.mi.jr;


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
	
	/**
	 * Un joueur est défini par un numéro de passage
	 */
	private int numJoueur;
	
	/**
	 * Constructeur
	 * @param couleur Couleur du jeton du joueur
	 * @param numJoueur Numéro de passage du joueur
	 */
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
	
	/**
	 * Getteur renvoyant le numéro de passage du joueur 
	 * @return le numéro de passage du joueur 
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
	
	
	/**
	 * Renvoie le numéro de la colonne dans laquelle le jeton est à placer dans la grille 
	 * @return le numéro de la colonne dans laquelle le jeton est à placer dans la grille
	 * @throws PuissanceException 
	 */
	public abstract int trouverPlacement(Plateau plateau) throws PuissanceException;

	
	
	
	
}
