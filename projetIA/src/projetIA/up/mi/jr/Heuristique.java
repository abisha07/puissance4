package projetIA.up.mi.jr;

public class Heuristique {
	
	private int MAX_SCORE;
	private int MIN_SCORE;
	
	public Heuristique() {
		MAX_SCORE = Integer.MAX_VALUE;
		MIN_SCORE = Integer.MIN_VALUE;
	}
	
	public double randomHeuristique() {
		return Math.random();
	}
	
	
	public int getMaxScore() {
		return MAX_SCORE;
	}
	
	public int getMinScore() {
		return MIN_SCORE;
	}

}
