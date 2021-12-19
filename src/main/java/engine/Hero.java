package engine;

import model.PacmanGame;
import model.PacmanPainter;

public class Hero {
	private static int abscisse=1;
	private static int ordonnee=1;
	private static int nombreVie = 3;
	private static int nombrePotion = 0;
	private static boolean potionEnCours=false; // vrai lorsque la potion est en train d'être utilisee
	private static long tempsLancementPotion;

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
			case SPACE:	// si on appuie sur espace et que le hero a une potion
				if (nombrePotion!=0) {
					tempsLancementPotion = System.currentTimeMillis();
					potionEnCours=true;
					nombrePotion=nombrePotion-1;
					System.out.println("utilise potion");
					PacmanGame.utilisePotion();
				}
				break;
		}
	}

	public static void computePos(int x,int y) {
		if(PacmanGame.check(x,y)) {
			changePos(x,y);
			PacmanGame.AjoutCle(x, y); //On verifie si on a bien ajoute une cle
			PacmanGame.verifArrivee(x, y); //On verifie si on est a l'arrivee
			PacmanGame.verifRetireMur(x, y); // on verifie si on est sur une case qui retire les murs
			PacmanGame.verifPotion(x,y); // on verifie si on est sur la case potion
			PacmanGame.verifVie(x, y); // on verifie si on est sur une case avec un point de vie 
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
	public static void ajoutVie() {
		nombreVie+=1;	
	}

	public static void retireVie() {
		nombreVie=nombreVie-1;
	}
	
	public static int getNombreVie() {
		return nombreVie;
	}
	
	public static void ajoutPotion() {
		nombrePotion = getNombrePotion() + 1;
	}
	
	public static int getNombrePotion() {
		return nombrePotion;
	}
	

	public static boolean isPotionEnCours() {
		return potionEnCours;
	}
	
	public static void potionPlusValide() {
		potionEnCours=false;
	}

	public static long getTempsLancementPotion() {
		return tempsLancementPotion;
	}
}