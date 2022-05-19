package projetIA.up.mi.jr;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe permettant de lancer une partie de Puissance 4 
 * @author Abisha Jeyavel, Lalarianiaina Ramanantoanina 
 *
 */
public class Partie {
	/**
	 * Objet de type Joueur représentant le premier joueur 
	 */
	private static Joueur joueur1;
	/**
	 * Objet de type Joueur représentant le second joueur 
	 */
	private static Joueur joueur2;
	
	//TODO
	private static int nbTour=1;
	
	
	/**
	 * Lit un entier au clavier
	 * 
	 * @param sc      le scanner dans lequel lire l'entier
	 * @param message le message a afficher avant la lecture
	 * @return l'entier lu
	 */
	private static int lireEntierAuClavier(Scanner sc) {
		boolean lectureOK = false;
		int nb = 0;
		while (!lectureOK) {
			try {
				nb = sc.nextInt();
				lectureOK = true ;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre entier");
				sc.nextLine();
			}
		}
		return nb;
	}
	
	
	/**
	 * Méthode pour définir le mode de Jeu (parmi 3)
	 * @param sc le Scanner pour récupérer le choix de l'utilisateur 
	 */
	public static int modeJeu(Scanner sc) {
		int choix;
		do {
		System.out.println("Vous avez 3 manières de lancer une partie de puissance 4");
		System.out.println("Quel mode de jeu voulez-vous choisir ? ");
		System.out.println("1 Joueur Humain contre Joueur Humain");
		System.out.println("2 Joueur Humain contre une IA");
		System.out.println("3 IA contre une IA");
		choix = lireEntierAuClavier(sc);
		if ((choix < 1) || (choix > 3)) {
			System.err.println("Le choix " + choix + " n'est pas valide,réitérez .");
			//System.exit(1);
		}		
		switch (choix) {
		case 1:
			joueur1 = new JoueurHumain('R',1);
			joueur2 = new JoueurHumain('J', 2);
			break;
			
		case 2:
			choixNiveauIAHumain(sc);
			break;
			
			
		case 3:
			choixNiveauIAIA(sc);
			break;
		}
		}while((choix < 1) || (choix > 3));
		return choix;
			
	}
	
	//TODO
	public static void choixNiveauIAHumain(Scanner sc) {
		System.out.println("Il existe 3 niveaux de difficulté, lequel voulez-vous ?");
		System.out.println("1, 2, ou 3 ? ");
		joueur2 = new JoueurHumain('R',1);
		int choixNiveauIAH = lireEntierAuClavier(sc);
		if ((choixNiveauIAH  < 1) || (choixNiveauIAH  > 3)) {
			System.err.println("Le choix " + choixNiveauIAH  + " n'est pas valide,réitérez .");
			//System.exit(1);
		}
		switch(choixNiveauIAH) {
		case 1:
			joueur1 = new IAAlphaBeta('J', 2, 5, 1);
			break;
			
		case 2:
			joueur1 = new IAMinimax('J', 2, 5);
			break;
			
		case 3:
			joueur1 = new IAAlphaBeta('J', 2, 5, 2);
			break;
		}
		
	}
	
	//TODO
	public static void choixNiveauIAIA(Scanner sc) {
		System.out.println("Il existe 3 niveaux de difficulté, lequel voulez-vous ?");
		System.out.println("1, 2, ou 3 ? ");
		int choixNiveauIAIA = lireEntierAuClavier(sc);
		if ((choixNiveauIAIA  < 1) || (choixNiveauIAIA  > 3)) {
			System.err.println("Le choix " + choixNiveauIAIA  + " n'est pas valide,réitérez .");
			//System.exit(1);
		}
		switch(choixNiveauIAIA) {
		case 1:
			joueur1 = new IAAlphaBeta('R', 1, 5, 1);
			joueur2 = new IAAlphaBeta('J', 2, 5, 1);
			break;
			
		case 2:
			// R qui gange pk ???
			joueur1 = new IAMinimax('R', 1, 5);
			joueur2 = new IAAlphaBeta('J', 2, 7, 2);
			//joueur2 = new IAMinimax('J', 2, 5);
			break;
		case 3:
			joueur1 = new IAAlphaBeta('R', 1, 7, 2);
			joueur2 = new IAAlphaBeta('J', 2, 7, 2);
			break;
		}
	}
	
	
	//TODO
	public static void jeuHumain(Scanner scanner, Plateau plateau ) throws PuissanceException {
		boolean place = false;
		while(!place) {
			System.out.println("Joueur " + plateau.getJoueurCourant().getNumJoueur() + ", veuillez entrer le numéro de la colonne du jeton entre 1 et 7 que vous voulez placer" );
			int col = lireEntierAuClavier(scanner);
			if(!(col > 0 && col <= 7)) {
				System.err.println("Numéro de la colonne incorrect, le numéro doit être compris entre 1 et 7, réitérez");
			}else if (!plateau.estCoupValide(col-1)) {
				System.err.println("Numéro de la colonne incorrect, la colonne est pleine, réitérez");
			}else {
				place=true;
				plateau.placerJeton(col-1, plateau.getJoueurCourant() );
			}
//			if((col > 0 && col <= 7) && plateau.estCoupValide(col-1)) {
//				place=true;
//				plateau.placerJeton(col-1, plateau.getJoueurCourant() );
//				
//			}else {
//				System.out.println("Numéro de la colonne incorrect, réitérez");
//			}
		
		}
	}
	
	
	//TODO
	public static void jeuIA(Plateau plateau) throws PuissanceException{
		boolean place = false;
		while(!place) {
			System.out.println("Joueur " + plateau.getJoueurCourant().getNumJoueur() + " est entrain de jouer");
			int col = plateau.getJoueurCourant().trouverPlacement(plateau);
			if((col >= 0 && col < 7) && plateau.estCoupValide(col)) {
				place=true;
				plateau.placerJeton(col, plateau.getJoueurCourant() );					
			}	
			
		}
	}
	
	

	public static void main(String [] args){
		System.out.println("Bienvenue dans le jeu Puissance 4");
		Scanner scanner = new Scanner(System.in);
		int mode = modeJeu(scanner);
		System.out.println("Joueur 1 a choisi la couleur " + joueur1.getCouleur() );
		System.out.println("Joueur 2 a choisi la couleur " + joueur2.getCouleur() );
		
		// Ajouter le choix de qui commence en premier !!!!!
		Plateau plateau = new Plateau(joueur1, joueur2,nbTour) ;
		plateau.affichePlateau();
		while(plateau.getGagnant() == ' ' && !plateau.estPlein()) {
			
			System.out.println("Tour n°" + plateau.getNbTour());
			
			try {
				switch (mode) {
				case 1:	
					jeuHumain(scanner, plateau);
					break;
					
				case 2:
					if  (plateau.getNbTour()%2==1) {
						jeuIA(plateau);
					}else{
						jeuHumain(scanner,plateau);
						
					}				
					break;	
				
				case 3:
					jeuIA(plateau);
					break;	
			}
				
			} catch (PuissanceException e) {
				System.out.println(e.getMessage());
			}
			plateau.affichePlateau();
			nbTour++;			
		}
		if (plateau.estPlein()) {
			System.out.println(" Egalité ");	
		}else {
			System.out.println("Le joueur ayant la couleur " + plateau.getGagnant() + " a gagné la partie !");	
		}
		
	}	
	
	

	
}
