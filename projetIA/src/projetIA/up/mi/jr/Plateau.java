package projetIA.up.mi.jr;


import java.util.Arrays;

/**
 * Représente le plateau de jeu du puissance 4
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class Plateau {
	/**
	 * tableau de type char représentant la grille du puissance 4
	 */
	char [][] grilleJeu;

	/**
	 * gagnant représente la couleur du jeton du joueur gagnant
	 */
	private char gagnant= ' ';
	
	/**
	 * joueur est un objet de Joueur, qui représente le premier joueur 
	 */
	private Joueur joueur1;
	
	/**
	 * joueur est un objet de Joueur, qui représente le second joueur 
	 */
	private Joueur joueur2;
	
	/**
	 * Représente le nombre de tour 
	 */
	private int nbTour;
	
	/**
	 * Constructeur qui initialise la grille avec 7 colonnes et 6 lignes 
	 */
	public Plateau(Joueur joueur1, Joueur joueur2, int nbTour) {
		grilleJeu = new char[6][7];
		//initialisation de la grille avec des points
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				grilleJeu[i][j] = '.';
			}
		}
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;	
		this.nbTour=nbTour;
	}
	

	
	/**
	 * Constructeur
	 * @param grille tableau de type char représentant la grille du puissance 4
	 * @param joueur1 représente le premier joueur 
	 * @param joueur2 représente le second joueur 
	 * @param nbTour représente le nombre de tour
	 */
	public Plateau(char[][] grille, Joueur joueur1, Joueur joueur2 , int nbTour) {
		grilleJeu = grille;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.nbTour=nbTour;
	}
	
	/**
	 * Getteur qui renvoie la couleur du jeton du joueur gagnant
	 * @return la couleur du jeton du joueur gagnant
	 */
	public char getGagnant() {
		return gagnant;
	}
	
	/**
	 * Getteur qui renvoie le joueur courant
	 * @return le joueur courant
	 */
	public Joueur getJoueurCourant() {
		if  (nbTour%2==1) {
			return joueur1;
		}else{
			return joueur2;
		}
		
	}
	
	/**
	 * Getteur qui renvoie le joueur suivant
	 * @return le joueur suivant
	 */
	public Joueur getJoueurSuivant() {
		if  (nbTour%2==1) {
			return joueur2;
		}else{
			return joueur1;
		}
		
	}
	
	/**
	 * Getteur qui renvoie le nombre de tour
	 * @return le nombre de tour
	 */
	public int getNbTour() {
		return nbTour;
	}
	
	
	/**
	 * Retourne une copie de l'objet Plateau
	 * @return une copie de l'objet Plateau
	 */
	public Plateau copieGrille(){
		char [][] copieGrille = new char[6][7];
		for (int t=0;t<copieGrille.length;t++) {
			copieGrille[t] = Arrays.copyOf(this.grilleJeu[t], this.grilleJeu[t].length);
		} 
		Plateau copieJeu = new Plateau(copieGrille, this.joueur1, this.joueur2, this.nbTour);

		return copieJeu;
	}
	
	
	/**
	 * Méthode pour afficher un plateau de jeu (graphiquement)
	 */
	public void affichePlateau() {
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				System.out.print(" | " + grilleJeu[i][j]);
			}
			System.out.println(" | ");
		}
	}
	
	
	
	/**
	 * Retourne vrai si la cellule pointee n'est pas en dehors du plateau
	 * @param ligne numéro de la ligne
	 * @param colonne numéro de la colonne
	 * @return vrai si la cellule pointee n'est pas en dehors du plateau et faux sinon
	 */
	public boolean valide(int ligne, int colonne) {
		return (ligne >= 0 && ligne < 6 && colonne >= 0 && colonne < 7);
	}
	
	
	/**
	 * Retourne la couleur de la cellule pointee 
	 * @param ligne numéro de la ligne
	 * @param colonne numéro de la colonne
	 * @return la couleur du jeton situé dans la case donnée en paramètre
	 */
	public char getCouleur(int ligne, int colonne) {
		return grilleJeu[ligne][colonne];
	}
	
	/** 
	 * Retourne vrai si le coup passe en parametre est jouable 
	 * @param colonne numéro de la colonne
	 * @throws PuissanceException 
	 * @return vrai si le coup passe en parametre est jouable et faux sinon
	 */
	public boolean estCoupValide(int colonne) throws PuissanceException {
		if(!(colonne >= 0 && colonne < 7)) {
			throw new PuissanceException("la colonne choisie est en dehors du plateau");
		}
		return (grilleJeu[0][colonne] == '.');
	}
	
	/** 
	 * Retourne la derniere ligne vide la colonne, -1 si la colonne est pleine 
	 * @param colonne numéro de la colonne
	 * @throws PuissanceException 
	 * @return dernière ligne vide associé à la colonne donnée en paramètre
	 */
	public int getLigneValide(int colonne) throws PuissanceException {
		if (!estCoupValide(colonne)) {
			return -1;
		}
		int ligne = 5;
		while (ligne >= 0 && getCouleur(ligne,colonne) != '.') {
			ligne--;
		}
		return ligne;
	}
	
	/** Retourne vrai si le plateau de jeu est complet 
	 * @return vrai si le plateau de jeu est complet et faux sinon
	 */
	public boolean estPlein() {
		boolean flag = true;
		int colonne = 0;
		while (flag && colonne <= 6) {
			flag &= (grilleJeu[0][colonne] != '.');
			colonne++;
		}
		return flag;
	}
	
	/** Retourne vrai si le plateau de jeu est vide 
	 * @return  vrai si le plateau de jeu est vide  et faux sinon
	 */
	public boolean estVide() {
		boolean flag = true;
		int colonne = 0;
		while (flag && colonne < 7) {
			flag &= (grilleJeu[5][colonne] == '.');
			colonne++;
		}
		return flag;
	}
	
	
	/**
	 * Change la couleur de la cellule pointee 
	 * @param ligne numéro de la ligne
	 * @param colonne numéro de la colonne
	 * @param couleur couleur du jeton à placer dans la case
	 */
	public void setCouleur(int ligne, int colonne, char couleur) {
		grilleJeu[ligne][colonne] = couleur;
	}
	
	
	/** Joue sur le plateau le coup passe en parametre  
	 * @throws PuissanceException */
	public int placerJeton(int colonne, Joueur joueur) throws PuissanceException {
		
		int ligne;
		ligne = getLigneValide(colonne);
		if(ligne != -1) {
			setCouleur(ligne, colonne, joueur.getCouleur());
			nbTour++;
		}
		if(aGagne(joueur, 4)){		
			gagnant = joueur.getCouleur();
		}
		return ligne;
		
	}
	
	/**
	 * Supprime le placement d'un jeton
	 * @param colonne numéro de la colonne
	 * @throws PuissanceException
	 */
	public void supprimePlacement(int colonne) throws PuissanceException {
		int ligne = getLigneValide(colonne);
		grilleJeu[ligne+1][colonne] = '.';	
		nbTour--;
	}
	

	/**
	 * Retourne vrai si le joueur passe en parametre a gagner
	 * @param joueur Objet de type joueur
	 * @param nbAlignement nbAlignement à vérifier pour ce joueur
	 * @return vrai si le joueur passe en parametre a gagner et faux sinon
	 */
	public boolean aGagne(Joueur joueur, int nbAlignement) {
		boolean res = false;
		for (int ligne = 0; ligne < 6 && !res; ligne++) {
			for (int colonne = 0; colonne < 7 && !res; colonne++) {
				if (getCouleur(ligne, colonne) == joueur.getCouleur()) {
					res = (chaine_max(ligne, colonne, joueur.getCouleur()) >= nbAlignement);
				}
			}
		}
		return res;
	}
	

	/**
	 * Methode qui renvoie la chaine maximum du jeton donne en parametre 
	 * @param ligne numéro de la ligne
	 * @param colonne numéro de la colonne
	 * @param joueur_jeton couleur du jeton qu'on veut connaitre la chaine maximum
	 * @return la chaine maximum du jeton donne en parametre 
	 */
	public int chaine_max(int ligne, int colonne, char joueur_jeton) {
   		// Cas où la couleur du jeton est vide, on a alignement de 0
   		if (joueur_jeton == '.') {
   			return 0;
   		}
   		
   		boolean boucle;
   		int chaineMax = 0;
   		int chaine = 1;
   		for (int dy : new int[] {-1, 0, 1}) {
   			for (int dx : new int[] {-1, 0, 1}) {
   				chaine = 1;
   				if (dy != 0 || dx != 0) {
   					for (int signe : new int[] {-1, 1}) {
   						int i = ligne;
   						int j = colonne;
   						do {
   							boucle = false;
   							i += signe * dy;
   							j += signe * dx;
   							if (valide(i, j) && getCouleur(i, j) == joueur_jeton) {
   								chaine++;
   								boucle = true;
   							}
   						} while (boucle);
   						if (chaine > chaineMax) {
   							chaineMax = chaine;
   						}
   					}
   				}
   			}
   		}
   		
   		return chaineMax;
   	}
	
	
	
}

