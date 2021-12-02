package engine;

import model.PacmanPainter;

public class Case {
	
// verifie que le héros est bien sur la case Clé	
	public static boolean verifCle(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==4) {
			return true;
		}
		return false;
	}
	
// verifie que le héros est bien sur la case arrivée	
	public static boolean verifArrivee(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==3) {
			return true;
		}
		return false;
	}
	
// verifie que le héros est bien sur la case Point de vie	
	public static boolean verifVie(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==5) {
			return true;
		}
		return false;
	}
	}
}
