package engine;

import model.PacmanPainter;

public class Case {
	
	public static boolean verifCle(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==4) {
			return true;
		}
		return false;
	}
	
	public static boolean verifArrivee(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==3) {
			return true;
		}
		return false;
	}
}
