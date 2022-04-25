package projetIA.up.mi.jr;

import java.util.ArrayList;

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
	public static Plateau instance = null;
	private char gagnant= ' ';
	public Joueur joueur;
	public Joueur joueurSuivant;
	/**
	 * Constructeur qui initialise la grille avec 7 colonne et 6 lignes 
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
	
	public Plateau(char[][] grille, Joueur joueur, Joueur joueurSuivant) {
		grilleJeu = grille;
		this.joueur = joueur;
		this.joueurSuivant = joueurSuivant;
	}
	

	
	
	public char getGagnant() {
		return gagnant;
	}
	
	public char[][] copieGrille(){
		char[][] copie = new char[6][7];
		for(int i = 0; i<6; i++) {
			for(int j=0; j<7; j++) {
				copie[i][j] =grilleJeu[i][j];
			}
			
		}
		return copie;
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
	public boolean valid(int line, int column) {
		return (line >= 0 && line < 6 && column >= 0 && column < 7);
	}
	
	/** Retourne la couleur de la cellule pointee */
	public char getColor(int line, int column) {
		return grilleJeu[line][column];
	}
	
	/** 
	 * Retourne vrai si le coup passe en parametre est jouable 
	 * @throws PuissanceException 
	 */
	public boolean isCoupValid(int column) throws PuissanceException {
		if(!(column >= 0 && column < 7)) {
			throw new PuissanceException("la colonne choisie est en dehors du plateau");
		}
		return (grilleJeu[0][column] == '.');
	}
	
	/** 
	 * Retourne la derniere ligne vide la colonne, -1 si la colonne est pleine 
	 * @throws PuissanceException 
	 */
	public int getLineValid(int column) throws PuissanceException {
		if (!isCoupValid(column)) {
			//System.out.println("exception");
			//throw new PuissanceException("la colonne est déja pleine");
			return -1;
		}
		int line = 5;
		while (line >= 0 && getColor(line,column) != '.') {
			line--;
		}
		return line;
	}
	
	/** Retourne vrai si le plateau de jeu est complet */
	public boolean isFull() {
		boolean flag = true;
		int column = 0;
		while (flag && column <= 6) {
			flag &= (grilleJeu[0][column] != '.');
			column++;
		}
		return flag;
	}
	
	/** Retourne vrai si le plateau de jeu est vide */
	public boolean isEmpty() {
		boolean flag = true;
		int column = 0;
		while (flag && column <= 6) {
			flag &= (grilleJeu[0][column] == '.');
			column++;
		}
		return flag;
	}
	
	
	/** Set la couleur de la cellule pointee */
	public void setColor(int line, int column, char color) {
		grilleJeu[line][column] = color;
	}

	/** Nettoie le plateau de jeu */
	public void clear() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				setColor(i, j, '.');
			}
		}
	}
	
	
	/** Joue sur le plateau le coup passe en parametre  
	 * @throws PuissanceException */
	public int placerJeton(int column, Joueur joueur) throws PuissanceException {
		
		int line;
		

			line = getLineValid(column);
			if(line != -1) {
				setColor(line, column, joueur.getCouleur());
				joueur.derniereColonne = column;
			}
			if(hasWon(joueur)){		
				gagnant = joueur.getCouleur();
				//affichePlateau();
				//System.out.println(joueur.toString() + " a gagné la partie !");
			}
			return line;
		
	}
	
	
	
	

	/** Retourne vrai si le joueur passe en parametre a gagner */
	public boolean hasWon(Joueur joueur) {
		boolean res = false;
		for (int line = 0; line < 6 && !res; line++) {
			for (int column = 0; column < 7 && !res; column++) {
				if (getColor(line, column) == joueur.getCouleur()) {
					res = (chaine_max(line, column, joueur.getCouleur()) >= 4);
				}
			}
		}
		return res;
	}
	
	/** methode qui renvoie la chaine maximum du jeton donne en parametre */
	public int chaine_max(int line, int column, char joueur_jeton) {
   		// Recupere le joueur qui a joue le dernier jeton
   		//int joueur_jeton = getColor(line, column);
   		if (joueur_jeton == '.') {
   			return 0;
   		}
   		
   		boolean loop;
   		int chaineMax = 0;
   		int chaine = 1;
   		for (int dy : new int[] {-1, 0, 1}) {
   			for (int dx : new int[] {-1, 0, 1}) {
   				chaine = 1;
   				if (dy != 0 || dx != 0) {
   					for (int sign : new int[] {-1, 1}) {
   						int i = line;
   						int j = column;
   						do {
   							loop = false;
   							i += sign * dy;
   							j += sign * dx;
   							if (valid(i, j) && getColor(i, j) == joueur_jeton) {
   								chaine++;
   								loop = true;
   							}
   						} while (loop);
   						if (chaine > chaineMax) {
   							chaineMax = chaine;
   						}
   					}
   				}
   			}
   		}
   		
   		return chaineMax;
   	}
	
	
	
	/**
	 * Méthode pour placer un jeton dans le plateau
	 * @param coordonnee du jeton
	 * @param couleur du jeton
	 * @throws PuissanceException  exception levé si on veut placer un jeton dans une colonne déjà pleine
	 */
	public void placerJetonV1(int colonne, Joueur joueur) throws PuissanceException {
		int rang = 6-1;
		while (grilleJeu[rang][colonne-1] != '.') {
			rang--;
		}
		
		if (grilleJeu[0][colonne -1] != '.') {
			System.out.println("exception");
			throw new PuissanceException("la colonne est déja pleine");
		}
		grilleJeu[rang][colonne-1] = joueur.getCouleur();
		
		
		ArrayList<Integer> coordonnee = new ArrayList<Integer>();
		coordonnee.add(rang);
		coordonnee.add(colonne);
		
		detectionVictoire(joueur, coordonnee);
		
		//System.out.println("HHHHH");
		
	}
	
	public void detectionVictoire(Joueur joueur, ArrayList<Integer> coordonnee) {
		//symbole en cours:
		//char symbole = (i%2==1 ? 'X' : 'O');

		int max = 0; //alignement maximale victoire si max >= 4
		int x; int y;
		int somme;
		
		//-->  diagonale HG-BD
		x = coordonnee.get(0); y = coordonnee.get(1)-1 ; somme=-1;
		while(y >= 0 && x >= 0 && grilleJeu[x][y] == joueur.getCouleur()){ y--; x--; somme++;}
		y = coordonnee.get(1)-1; x = coordonnee.get(0);
		while(x < 6 && y < 7 && grilleJeu[x][y] == joueur.getCouleur()){ x++; y++; somme++;}
		if(somme > max) max= somme;
		
		//-->  diagonale HD-BG
		y = coordonnee.get(1)-1; x = coordonnee.get(0); somme=-1;
		while(x >= 0 && y < 7 && grilleJeu[x][y] == joueur.getCouleur()){ x--; y++; somme++;}
		y = coordonnee.get(1)-1; x = coordonnee.get(0);
		while(x < 6 && y >= 0 && grilleJeu[x][y] == joueur.getCouleur()){ x++; y--; somme++;}
		if(somme > max) max= somme;
		
		//-->  verticale:
		y = coordonnee.get(1)-1; x = coordonnee.get(0); somme=0; //pour y -1 car tableau en prog commence à 0
		//somme nb de symboles identiques et d'affilés
		// car dans puissance 4 qd on place jeton vericalement y'a rien au dessus
		//while(x >= 0 && grilleJeu[x][y] == couleur){ x--; somme++;}// tant qu'on ne sort pas du tab, on remonte vers le haut
		//x = coordonnee.get(0);
		while(x < 6 && grilleJeu[x][y] == joueur.getCouleur()){ x++; somme++;} //verif vers le bas pour pas sortir du tab
		if(somme > max) max= somme;
		
		//-->  horizontale:
		y = coordonnee.get(1)-1; x = coordonnee.get(0); somme=-1;
		while(y >= 0 && grilleJeu[x][y] == joueur.getCouleur()){ y--; somme++;}
		y = coordonnee.get(1)-1;
		while(y < 7 && grilleJeu[x][y] == joueur.getCouleur()){ y++; somme++;}
		if(somme > max) max= somme;
		
		
		if(max >= 4){		
			gagnant = joueur.getCouleur();
			//affichePlateau();
			//System.out.println(joueur.toString() + " a gagné la partie !");
		}
		//return gagnant;
	}
	
	
	public boolean chercheAlignementDeJeton(char joueurCouleur,int ligne, int colonne, int declinaisonHorizontale, int declinaisonVerticale){
		boolean alignementPreserve = true;
		int tailleAlignement = 4;
		System.out.println("numlignedep");
		System.out.println(ligne);
		System.out.println("numColonnedep");
		System.out.println(colonne);
		//double resultat = 1;
		while(tailleAlignement != 0 && alignementPreserve){
			// On cherche l'alignement de la taille demandé
			char jetonCouleur = 0 ;
			// On récupère le jeton correspondant à i j pour vérifier sa couleur
			//jetonCouleur = joueur.getCouleur();
//			System.out.println("numligne");
//			System.out.println(ligne);
//			System.out.println("numColonne");
//			System.out.println(colonne);
			//this.affichePlateau();
			jetonCouleur = this.getColor(ligne, colonne);
			//System.out.println(joueurCouleur);
			//System.out.println("JetonCouleur");
			//System.out.println(jetonCouleur);
			// On teste la couleur du jeton
			
			if(jetonCouleur == '.' || jetonCouleur != joueurCouleur) {
				//resultat *= 0.5;
				alignementPreserve = false;
//			}else if( jetonCouleur == joueur.getCouleur()){
//				resultat *= 1.0;
//			}else{
//				resultat *= 0;
//			}
			// On cherche sur la prochaine et on réduit le nombre de cases à chercher et donc l'alignement
			
			ligne+=declinaisonHorizontale;
			colonne+=declinaisonVerticale;
			System.out.println(tailleAlignement);
			tailleAlignement--;
			}
		}
		//return resultat;
		System.out.println("PB");
		System.out.println(alignementPreserve);
		return alignementPreserve;
	}
	
	
	public double resultatAlignementDeJeton(char joueurCouleur, int ligne , int colonne, int declinaisonHorizontale, int declinaisonVerticale){
		
		int tailleAlignement = 4;
		double resultat = 1;
		while(tailleAlignement != 0 && resultat != 0 && this.valid(ligne, colonne) ){
			// On cherche l'alignement de la taille demandé
			char jetonCouleur = 0 ;
			// On récupère le jeton correspondant à i j pour vérifier sa couleur
			//System.out.println("ligne" + ligne+1);
			//System.out.println("colonne" + colonne+1);
			jetonCouleur = grilleJeu[ligne][colonne];
			//System.out.println(jetonCouleur);
			// On teste la couleur du jeton
			if(jetonCouleur == '.') {
				resultat *= 0.5;
			}else if(jetonCouleur == joueurCouleur){
				resultat *= 1.0;
			}else{
				resultat *= 0;
			}
			// On cherche sur la prochaine et on réduit le nombre de cases à chercher et donc l'alignement
			ligne+=declinaisonHorizontale;
			colonne+=declinaisonVerticale;
			tailleAlignement--;
		}
		return resultat;
	}
	
	
	public boolean chercheAlignement(char joueurCouleur){
			
			boolean alignementTrouve = false;
			// On fait une recherche horizontale sur toute les cases possibles
			for(int j = 1; j <= 6 && !alignementTrouve; j++){
				for (int i = 1; i <= 7 && !alignementTrouve; i++){
					System.out.println("indice I");
					System.out.println(i);
					System.out.println("indice J");
					System.out.println(j);
					alignementTrouve = this.chercheAlignementDeJeton(joueurCouleur, i, j,0, 1)||				
							this.chercheAlignementDeJeton(joueurCouleur, i, j, 1, 1) ||
							this.chercheAlignementDeJeton(joueurCouleur, i, j, 1, 0) ||
							this.chercheAlignementDeJeton(joueurCouleur, i, j, 1, -1) ||
							this.chercheAlignementDeJeton(joueurCouleur, i, j, 0, -1) ||
							this.chercheAlignementDeJeton(joueurCouleur, i, j, -1, -1) ||
							this.chercheAlignementDeJeton(joueurCouleur, i, j, -1, 0) || 
							this.chercheAlignementDeJeton(joueurCouleur, i, j,-1, 1);
	
				}
			}
			System.out.println("chercheALIgnement");
			System.out.println(alignementTrouve);
			return alignementTrouve;
		}
	
}
