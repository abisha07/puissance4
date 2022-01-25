package projetIA.up.mi.jr;

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
