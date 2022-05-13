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
	 * joueur est un objet de Joueur, qui représente le joueur courant
	 */
	public Joueur joueur;
	
	/**
	 * joueur est un objet de Joueur, qui représente le joueur suivant
	 */
	public Joueur joueurSuivant;
	
	/**
	 * Constructeur qui initialise la grille avec 7 colonnes et 6 lignes 
	 */
	public Plateau(Joueur joueur, Joueur joueurSuivant) {
		grilleJeu = new char[6][7];
		//initialisation de la grille avec des points
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				grilleJeu[i][j] = '.';
			}
		}
		this.joueur = joueur;
		this.joueurSuivant = joueurSuivant;		
	}
	
	public void  setJoueur(Joueur jCourant,Joueur jSuivant) {
		this.joueur = jCourant;
		this.joueurSuivant = jSuivant;		
		
	}
	
	/**
	 * Constructeur
	 * @param grille tableau de type char représentant la grille du puissance 4
	 * @param joueur représente le joueur courant
	 * @param joueurSuivant représente le joueur suivant
	 */
	public Plateau(char[][] grille, Joueur joueur, Joueur joueurSuivant) {
		grilleJeu = grille;
		this.joueur = joueur;
		this.joueurSuivant = joueurSuivant;
	}
	
	/**
	 * Getteur qui renvoie la couleur du jeton du joueur gagnant
	 * @return la couleur du jeton du joueur gagnant
	 */
	public char getGagnant() {
		return gagnant;
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
		Plateau copieJeu = new Plateau(copieGrille, this.joueur, this.joueurSuivant);

		return copieJeu;
	}
	
	
	/**
	 * Méthode pour créer un plateau de jeu vide (graphiquement)
	 */
	public void affichePlateau() {
		for(int i=0; i<6; i++) {
			for(int j = 0; j<7; j++) {
				System.out.print(" | " + grilleJeu[i][j]);
			}
			System.out.println(" | ");
		}
	}
	
	
	/** Retourne vrai si la cellule pointee n'est pas en dehors du plateau */
	public boolean valide(int ligne, int colonne) {
		return (ligne >= 0 && ligne < 6 && colonne >= 0 && colonne < 7);
	}
	
	
	/** Retourne la couleur de la cellule pointee */
	public char getCouleur(int ligne, int colonne) {
		return grilleJeu[ligne][colonne];
	}
	
	/** 
	 * Retourne vrai si le coup passe en parametre est jouable 
	 * @throws PuissanceException 
	 */
	public boolean estCoupValide(int colonne) throws PuissanceException {
		if(!(colonne >= 0 && colonne < 7)) {
			throw new PuissanceException("la colonne choisie est en dehors du plateau");
		}
		return (grilleJeu[0][colonne] == '.');
	}
	
	/** 
	 * Retourne la derniere ligne vide la colonne, -1 si la colonne est pleine 
	 * @throws PuissanceException 
	 */
	public int getLigneValide(int colonne) throws PuissanceException {
		if (!estCoupValide(colonne)) {
			//System.out.println("exception");
			//throw new PuissanceException("la colonne est déja pleine");
			return -1;
		}
		int ligne = 5;
		while (ligne >= 0 && getCouleur(ligne,colonne) != '.') {
			ligne--;
		}
		return ligne;
	}
	
	/** Retourne vrai si le plateau de jeu est complet */
	public boolean estPlein() {
		boolean flag = true;
		int colonne = 0;
		while (flag && colonne <= 6) {
			flag &= (grilleJeu[0][colonne] != '.');
			colonne++;
		}
		return flag;
	}
	
	/** Retourne vrai si le plateau de jeu est vide */
	public boolean estVide() {
		boolean flag = true;
		int colonne = 0;
		while (flag && colonne < 7) {
			flag &= (grilleJeu[5][colonne] == '.');
			colonne++;
		}
		return flag;
	}
	
	
	/** Set la couleur de la cellule pointee */
	public void setCouleur(int ligne, int colonne, char couleur) {
		grilleJeu[ligne][colonne] = couleur;
	}

	/** Nettoie le plateau de jeu */
	public void vider() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				setCouleur(i, j, '.');
			}
		}
	}
	
	
	/** Joue sur le plateau le coup passe en parametre  
	 * @throws PuissanceException */
	public int placerJeton(int colonne, Joueur joueur) throws PuissanceException {
		
		int ligne;
		ligne = getLigneValide(colonne);
		if(ligne != -1) {
			setCouleur(ligne, colonne, joueur.getCouleur());
			joueur.derniereColonne = colonne;
		}
		if(aGagne(joueur, 4)){		
			gagnant = joueur.getCouleur();
		}
		return ligne;
		
	}
	
	

	/** Retourne vrai si le joueur passe en parametre a gagner */
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
	
	/**methode qui renvoie la chaine maximum du jeton donne en parametre */
	public int chaine_max(int ligne, int colonne, char joueur_jeton) {
   		// Recupere le joueur qui a joue le dernier jeton
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
	
	
	public void supprimePlacement(int colonne) throws PuissanceException {
		int ligne = getLigneValide(colonne);
		grilleJeu[ligne+1][colonne] = '.';	
	}
	
}
