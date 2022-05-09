package projetIA.up.mi.jr;

/**
 * Classe qui gère les erreurs dans le jeu Puissance 4
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class PuissanceException extends Exception {
	
	
	/**
	 * SerialVersion 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur à partir d'un message
	 * @param m message à afficher lors de l'erreur
	 */
	public PuissanceException(String m) {
		super(m);
	}

}
