package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Case;
import engine.Cmd;
import engine.Game;
import engine.Hero;

import java.time.Instant;

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
	
	public static boolean check(int abscisse,int ordonnee) {	// checker si la case n'est pas occup�e par un mur
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
			System.out.println("Vous avez une clé");

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
			finJeu = true; //On a dépassé le temps maximal autorisé donc on a perdu
			System.out.print("Partie perdue: temps dépassé");
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
			finJeu = true; //On a dépassé le temps maximal autorisé donc on a perdu
			System.out.print("Partie perdue: temps dépassé");
			return false;
		}
	} */
	
	/*
	public void AjoutCle(int x, int y) {		// pas utile finalement: mis dans finJeu
		if (Case.verifCle(x,y)==true) {
			NombreCle+=1;
		}
	}						// � enlever: la cl� du plateau
	*/


	//verifier si le jeu est fini, c'est à dire qu'on est sur la case arrivée avec au moins une clé
	
	public static boolean verifArrivee (int abscisse, int ordonnee) {
		
		if (NombreCle>= 1 && Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])==3){
			System.out.println("Le jeu est gagné!");
			finJeu = true;
			return true;
		}
			return false;
	}

	@Override
	public boolean finJeu() {
		if ( finJeu == true) {
			return true;
		}
		return false;
	}
	
}
