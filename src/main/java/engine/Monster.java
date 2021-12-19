package engine;

import java.io.BufferedReader;

import model.PacmanGame;

import java.awt.event.KeyEvent;

public class Monster {
	public static int abscisse=18;
	public static int ordonnee=1;
	private static Cmd commandeMonster;
	private static boolean monstreEnVie=true;


	public static void aleatoire() {
		int commandealeatoire=1+(int)(Math.random() * 4);
		//System.out.println(commandealeatoire);
		//aleat renvoie un nombre aleatoire entre 0 et 3, qui correspondra a  la position dans la liste
		if (commandealeatoire==1) {
			commandeMonster=Cmd.LEFT;
		}
		if (commandealeatoire==2) {
			commandeMonster=Cmd.UP;
		}
		if (commandealeatoire==3) {
			commandeMonster=Cmd.RIGHT;
		}
		if (commandealeatoire==4) {
			commandeMonster=Cmd.DOWN;
		}
	}
	
	public static void move(Cmd commande) {
		//System.out.println(commande);
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
	
	public static void changePos(int x,int y) {
		abscisse=x;
		ordonnee=y;
		//System.out.println("position x :"+x+"; position y :"+y);
	}
		
	public static void computePos(int x,int y) {
		if(PacmanGame.check(x,y)) {
			changePos(x,y);   //que ca dans monster
		}
	}

	public static int getAbscisse() {
		return abscisse;
	}
	
	public static int getOrdonnee() {
		return ordonnee;
	}

	public static Cmd getCommandeMonster() {
		return commandeMonster;
	}

	public static boolean isMonstreEnVie() {
		return monstreEnVie;
	}
}

