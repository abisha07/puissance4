package projetIA.up.mi.jr;

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
	private static int nbTour=0;
	
	/**
	 * Méthode pour définir le mode de Jeu (parmi 3)
	 * @param sc le Scanner pour récupérer le choix de l'utilisateur 
	 */
	public static void modeJeu(Scanner sc) {
		int choix;
		do {
		System.out.println("Vous avez 3 manière de lancer une partie de puissance 4");
		System.out.println("Quelle mode de jeu voulez-vous choisir ? ");
		System.out.println("1 Joueur Humain contre Joueur Humain");
		System.out.println("2 Joueur Humain contre une IA");
		System.out.println("3 IA contre une IA");

		choix = sc.nextInt();
		if ((choix < 1) || (choix > 3)) {
			System.err.println("Le choix " + choix + " n'est pas valide.");
			System.exit(1);
		}		
		switch (choix) {
		case 1:
			choixPerso(sc,1, 0);// humain contre humain est par défaut de niveau 1
			break;
			
		case 2:
			System.out.println("Il existe 4 niveaux de difficulté, lequel voulez-vous ?");
			System.out.println("1, 2, 3 ou 4 ? ");
			int choixNiveau = sc.nextInt();
			choixPerso(sc, 2, choixNiveau);
			break;
			
		case 3:
			break;
		}
		}while((choix < 1) || (choix > 3));
			
	}
	
	/**
	 * Méthode pour définir les couleurs des jetons des joueurs 
	 * @param sc le Scanner pour récupérer le choix de l'utilisateur 
	 * @param mode Entier pour identifier les 2 types de modes 
	 * mode = 1 joueur humain contre un autre joueur humain 
	 * mode = 2 joueur humain contre une IA
	 * @param niveau
	 */
	public static void choixPerso(Scanner sc, int mode, int niveau) {
		
		
		if(mode == 1) { //Si le joueur humain joue contre un autre joueur humain
			System.out.println("Joueur1, quelle couleur de jeton voulez-vous choisir pour cette partie");
			System.out.println("Saisir \"rouge\" ou \"jaune\"");
			String choixJeton = sc.nextLine();
			if (choixJeton.equalsIgnoreCase("rouge")){
				joueur1 = new JoueurHumain('R',1);
				joueur2 = new JoueurHumain('J', 2);
			}
			else { 
				joueur1 = new JoueurHumain('J', 1);
				joueur2 = new JoueurHumain('R', 2);
			}
		}
//		else { // Cas joueur humain joue contre l'IA
//			if (niveau == 1) {
//				IAbasique joueur2 = new IAbasique();
//			}else if (niveau == 2) {
//				IAMonteCarlo joueur2 = new IAMonteCarlo();
//			}else if (niveau == 3) {
//				IAQLearning joueur2 = new IAQLearning();
//			}else if (niveau == 4) {
//				IAalphaBeta joueur2 = new IAalphaBeta();
//			}
//		}
		System.out.println("Joueur 1 a choisi la couleur " + joueur1.getCouleur() );
		System.out.println("Joueur 2 a choisi la couleur " + joueur2.getCouleur() );
	}

	public static void main(String [] args){
		System.out.println("Bienvenue dans le jeu Puissance 4");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel mode voulez-vous choisir ?");
		modeJeu(scanner);
		//System.out.println("Joueur 1, voulez-vous commencer la partie ? y/n");
		//Scanner sc = new Scanner(System.in);
		//String rep = sc.nextLine();
		Plateau plateau = new Plateau();
		//plateau.affichePlateau();
		while(plateau.getGagnant() == ' ' && nbTour < 6*7) {
			System.out.println("Tour n°" + (nbTour+1));
			plateau.affichePlateau();
			System.out.println("Joueur" + (nbTour%2==0 ? 1:2) + ", veuillez entrer le numéro de la colonne du jeton que vous voulez placer" );
			int col = scanner.nextInt();
			try {
				plateau.placerJeton(col, nbTour%2==0 ? joueur1 : joueur2);
			} catch (PuissanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nbTour++;
			
		}
			
		}
		
		
		
//		if (rep == "y") { // Cas où joueur 1 joue lors des tours impairs car il commence 
//			for(int i = 1; i <= 7*6; i++) {
//				System.out.println("Tour : " + i);
//				//if(detectionVictoireEgalite){ 
//				// afficher msg de la situation
//				//i = 47; //pour sortir de la boucle
//				//}else{
//				if(i%2 == 1) {
//					joueur1.trouverPlacement();
//					//plateau.placerJeton(joueur1.trouverPlacement());
//				}else {
//					joueur2.trouverPlacement();
//					//plateau.placerJeton(joueur2.trouverPlacement());
//				}
//				//plateau.affichePlateau();
//			}
//		}else { //Cas où joueur 2 joue lors des tours impairs 
//			for(int i = 1; i <= 7*6; i++) {
//				//if(detectionVictoireEgalite){ 
//				// afficher msg de la situation
//				//i = 47; //pour sortir de la boucle
//				//}else{
//				if(i%2 == 1) {
//					joueur2.trouverPlacement();
//					//plateau.placerJeton(joueur1.trouverPlacement());
//				}else {
//					joueur1.trouverPlacement();
//					//plateau.placerJeton(joueur2.trouverPlacement());
//				}
//			}
//		}
//	sc.close();
//	}
	
	
}
