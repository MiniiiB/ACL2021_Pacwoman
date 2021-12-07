package engine;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


import model.PacmanGame;
import model.PacmanPainter;
import engine.Case;

public class Hero {
	private static int abscisse=1;
	private static int ordonnee=1;
	private static int NombreVie = 3;

	public static void move(Cmd commande) {
		switch (commande) {
			case LEFT:
				computePos(getAbscisse()-1,ordonnee);
				break;
			case UP:
				computePos(getAbscisse(),ordonnee-1); // -1 car on veut labyrinthe[indice-1]
				break;
			case RIGHT:
				computePos(getAbscisse()+1,ordonnee);
				break;
			case DOWN:
				computePos(getAbscisse(),ordonnee+1);	// +1 car on veut labyrinthe[indice+1]
				break;
			}
	}

	
	public static void computePos(int x,int y) {
		if(PacmanGame.check(x,y)) {
			changePos(x,y);
			PacmanGame.AjoutCle(x, y); //On verifie si on a bien ajoute une cle
			PacmanGame.verifArrivee(x, y); //On verifie si on est�a l'arrivee
			PacmanGame.verifRetireMur(x, y);
			PacmanGame.verifVie(x, y); // on verifie si on est sur une case avec un point de vie
			PacmanGame.getTime(); //On verifie si le temps n'est pas depasse 
		}
	}
	
	public static void changePos(int x,int y) {
		abscisse=x;
		ordonnee=y;
		//PacmanPainter.drawPacman(x,y); // les coordonnees changent maintenant il faut faire bouger le point sur le graphique
	}

	public static int getAbscisse() {
		return abscisse;
	}
	public static int getOrdonnee() {
		return ordonnee;
	}


//ajouter une vie au heros quand il passe sur la case "vie"	
	public static int ajoutVie(int abscisse, int ordonnee) {
		if (Case.verifVie(abscisse,ordonnee) && getNombreVie()<3) {
			NombreVie+=1;
			System.out.println("Vous avez gagn� une vie");
			PacmanPainter.retirePdvPlateau(abscisse,ordonnee);
		}
		return getNombreVie();
	}

	public static void retireVie() {
		if (PacmanGame.verifMonster() == true) {
			NombreVie=NombreVie-1;
		}
	}
	
	public static int getNombreVie() {
		return NombreVie;
	}
	
}