package engine;



import model.PacmanGame;
import model.PacmanPainter;


public class Hero {
	private static int abscisse=1;
	private static int ordonnee=1;
	private static int nombreVie = 2;

	public static void move(Cmd commande) {
		switch (commande) {
			case LEFT:
				computePos(getAbscisse()-1,getOrdonnee());
				break;
			case UP:
				computePos(getAbscisse(),getOrdonnee()-1); // -1 car on veut labyrinthe[indice-1]
				break;
			case RIGHT:
				computePos(getAbscisse()+1,getOrdonnee());
				break;
			case DOWN:
				computePos(getAbscisse(),getOrdonnee()+1);	// +1 car on veut labyrinthe[indice+1]
				break;
		}
	}

	public static void computePos(int x,int y) {
		if(PacmanGame.check(x,y)&& nombreVie>0) {
			changePos(x,y);
			PacmanGame.AjoutCle(x, y); //On verifie si on a bien ajoute une cle
			PacmanGame.verifArrivee(x, y); //On verifie si on est a l'arrivee
			PacmanGame.verifRetireMur(x, y);
			PacmanGame.verifVie(x, y); // on verifie si on est sur une case avec un point de vie
			PacmanGame.verifMonster(abscisse, ordonnee, Monster.abscisse, Monster.ordonnee);
			PacmanGame.getTime(); //On verifie si le temps n'est pas depasse 
		}
	}
	
	
	public static void changePos(int x,int y) {
		abscisse=x;
		ordonnee=y;
	}

	public static int getAbscisse() {
		return abscisse;
	}
	public static int getOrdonnee() {
		return ordonnee;
	}

	//ajouter une vie au heros quand il passe sur la case "vie"	
	public static int ajoutVie(int abscisse, int ordonnee) {
		if (PacmanGame.verifVie(abscisse,ordonnee) && nombreVie<3) {
			nombreVie+=1;
			System.out.println("Vous avez gagne	 une vie");
			PacmanPainter.retireObjet(abscisse,ordonnee);
		}
		return getNombreVie();
	}

	public static void retireVie() {
		nombreVie=nombreVie-1; 
	}
	
	public static int getNombreVie() {
		return nombreVie;
	}
	
}