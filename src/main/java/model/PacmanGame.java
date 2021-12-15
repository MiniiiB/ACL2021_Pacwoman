package model;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import engine.Hero;
import engine.Monster;


//Version avec personnage qui peut se deplacer. A completer dans les versions suivantes.
public class PacmanGame implements Game {
	private static int NombreCle=0;
	private static long tempsDepart = System.currentTimeMillis();
	//private static Instant TempsDepart=Instant.now();
	private static long TempsMax= 30000; //C'est en milisecondes donc ici on dispose de 30s pour finir le jeu
	

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
		//System.out.println("Execute "+commande);
		//System.out.println("Execute "+Hero.getAbscisse()+Hero.getOrdonnee());
		Hero.move(commande);
		Monster.aleatoire();
		//System.out.println(Monster.getCommandeMonster());
		Monster.move(Monster.getCommandeMonster());
		getTime();
		
		
	}
	
	public static boolean check(int abscisse,int ordonnee) {	// checker si la case n'est pas occupee par un mur
		//System.out.println("case 4 "+PacmanPainter.getLabyrinthe()[abscisse][ordonnee]);
		if (PacmanPainter.getLabyrinthe()[abscisse][ordonnee]!=null) {
			//System.out.println("case 3 "+PacmanPainter.getLabyrinthe()[abscisse][ordonnee]);
			if (Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])!=1) {  //Si ce n'est pas un mur
				//System.out.println("commande");
				return true;  
			}
			//System.out.println("commande");
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
			//System.out.println(Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee]));
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

	public static String gettime(){
		long tempsEcoule = System.currentTimeMillis() - tempsDepart;
		long tps = tempsEcoule / 1000;
		String s = String.valueOf(tps);
		return s;
	}
	
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
	
	public static boolean verifMonster(int abscisseHeros, int ordonneeHeros,int abscisseMonster, int ordonneeMonster) {
		// Ce programme permet de verifier si le heros est sur la meme case que le monstre, si oui il renvoie True
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisseHeros][ordonneeHeros])==Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisseMonster][ordonneeMonster])){
			Hero.retireVie();
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
