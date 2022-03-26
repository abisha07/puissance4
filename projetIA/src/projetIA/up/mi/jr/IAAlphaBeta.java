package projetIA.up.mi.jr;



public class IAAlphaBeta extends Joueur{
	//private int niveau = 2;

		private int profondeur;
		private Heuristique heuristique;
	
	public IAAlphaBeta(char couleurJeton, int numJoueur,int profondeur) {
		super(couleurJeton, numJoueur);
		this.profondeur = profondeur;
		heuristique = new Heuristique();
	}
	



	@Override
	public int trouverPlacement(Plateau plateau) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private double alphabeta(Plateau plateau, Joueur joueur, int profondeur){
		double alpha = heuristique.getMinScore();
		double beta= heuristique.getMaxScore();
		return this.min(plateau, joueur, profondeur, alpha, beta);
	}
	
	private double min(Plateau plateau, Joueur joueur,  int profondeur, double alpha, double beta){
		if(profondeur != 0){
			double valeurDeJeu = Heuristique.MAX_NOTE;
			for(int i=1; i <= Grille.LARGEUR_GRILLE ; i++){
				try {
					if(!grille.estColonnePleine(i)){
						Grille copieDeLaGrille = grille.copie();
						Jeton jeton = new Jeton(Partie.getPartie().joueurSuivant(joueur).getCouleur());
						copieDeLaGrille.insereJeton(i, jeton);
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieDeLaGrille, joueur, profondeur-1, alpha, beta));
						
						if(alpha >= valeurDeJeu){
							return valeurDeJeu; // Coupure alpha
						}
						
		               beta = Math.min(beta, valeurDeJeu);
						
					}
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}catch (ColonnePleineException e) {
					e.printStackTrace();
				} 
			}
			return valeurDeJeu;
		}else{
			return this.heuristique.noteGrille(grille, joueur );
		}
	}
	

}
