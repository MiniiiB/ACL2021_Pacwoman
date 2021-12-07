package model;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import engine.Hero;


//Version avec personnage qui peut se deplacer. A completer dans les versions suivantes.
public class PacmanGame implements Game {
	private static int NombreCle=0;
	private static long tempsDepart = System.currentTimeMillis();
	//private static Instant TempsDepart=Instant.now();
	private static long TempsMax= 20000; //C'est en milisecondes donc ici on dispose de 20s pour finir le jeu
	

	//constructeur avec fichier source pour le help
	public PacmanGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
	}
	//Boolean signifiant la fin du jeu
	static boolean finJeu = false;

	//faire evoluer le jeu suite a une commande @param commande
	@Override
	public void evolve(Cmd commande) {
		System.out.println("Execute "+commande);
		System.out.println("Execute "+Hero.getAbscisse()+Hero.getOrdonnee());
		Hero.move(commande);
	}
	
	public static boolean check(int abscisse,int ordonnee) {	// checker si la case n'est pas occupee par un mur
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])!=1) {  //Si ce n'est pas un mur
			return true;  
		}
		return false;
	}
	
	public boolean possedeCle() {
		if (NombreCle!=0) {
			return true;
		}
		return false;
	}

	public static void AjoutCle(int abscisse, int ordonnee) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])==4) {
			NombreCle = NombreCle + 1;
			System.out.println("Vous avez une cle");
			PacmanPainter.retireClePlateau(abscisse,ordonnee);
		}
	}
	
	// verifier si le heros a bien au moins une vie, sinon le jeu se finit
		public static boolean verifEnVie(int abscisse, int ordonnee) {
			if (Hero.getNombreVie()>0) {
				return true;
			}
			else {
				finJeu=true;
				return false; 
				}
		}
	
	public static boolean getTime(){
		long tempsEcoule = System.currentTimeMillis() - tempsDepart;
		if (tempsEcoule > TempsMax) {
			finJeu = true; //On a depasse le temps maximal autorise donc on a perdu
			System.out.print("Partie perdue: temps depasse");
			return true;
		}
		else {
			return false;
		}
	}

	//TIMER DE ELOISE QUE JE N'AI PAS SU FAIRE BIEN FONCITONNER

	/* public static long GetTime() {
		Instant TempsEcoule= Instant.now();
		long TempsEcouleconverti = TempsEcoule.toEpochMilli();
		long TempsDepartconverti = TempsDepart.toEpochMilli();
		return (TempsEcouleconverti-TempsDepartconverti);
	}
	public static boolean OkTime() {
		if (GetTime()<=TempsMax){
			return true;
		}
		else {
			finJeu = true; //On a depasse le temps maximal autorise donc on a perdu
			System.out.print("Partie perdue: temps depasse");
			return false;
		}
	} */
	
	/*
	public void AjoutCle(int x, int y) {		// pas utile finalement: mis dans finJeu
		if (Case.verifCle(x,y)==true) {
			NombreCle+=1;
		}
	}						// a enlever: la cle du plateau
	*/


	//verifier si le jeu est fini, c'est a  dire qu'on est sur la case arrivee avec au moins une cle
	
	public static boolean verifArrivee (int abscisse, int ordonnee) {
		
		if (NombreCle>= 1 && Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])==3){
			System.out.println("Le jeu est gagne!");
			finJeu = true;
			return true;
		}
		return false;
	}
	
	public static boolean verifVie(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==5) {
			return true;
		}
		return false;
	}
	
	public static void verifRetireMur(int x, int y) {
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==6) {
			PacmanPainter.retireMursPlateau(x,y);
		}
	}

	@Override
	public boolean finJeu() {
		if ( finJeu == true) {
			return true;
		}
		return false;
	}

	public static boolean verifMonster() { // a faire 
		// TODO Auto-generated method stub
		return false;
	}
	
}
