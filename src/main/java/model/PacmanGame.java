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
		verifEnVie();
		utilisePotion();
		Hero.move(commande);
		if (Monster.isMonstreEnVie()) {
			Monster.aleatoire();
			//System.out.println(Monster.getCommandeMonster());
			Monster.move(Monster.getCommandeMonster());
			PacmanGame.verifMonster(Hero.getAbscisse(), Hero.getOrdonnee(), Monster.abscisse, Monster.ordonnee);
			getTime();
		}	
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
			PacmanPainter.retireObjet(abscisse,ordonnee);
			//System.out.println(Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee]));
			PacmanPainter.ajoutArrivee(18,18);
		}
		
	}
	
	// verifier si le heros a bien au moins une vie, sinon le jeu se finit
		public static void verifEnVie() {
			if (Hero.getNombreVie()<=0) {
				finJeu=true;
			}
		}
	
	public static void getTime(){
		long tempsEcoule = System.currentTimeMillis() - tempsDepart;
		if (tempsEcoule > TempsMax) {
			finJeu = true; //On a depasse le temps maximal autorise donc on a perdu
			System.out.print("Partie perdue: temps depasse");
		}
	}

	public static String gettime(){
		long tempsEcoule = System.currentTimeMillis() - tempsDepart;
		long tps = tempsEcoule / 1000;
		String s = String.valueOf(tps);
		return s;
	}
	
	public static void verifArrivee (int abscisse, int ordonnee) {
		if (NombreCle>= 1 && Integer.parseInt(PacmanPainter.getLabyrinthe()[abscisse][ordonnee])==3){
			System.out.println("Le jeu est gagne!");
			finJeu = true;
		}
	}
	
	public static void verifVie(int x, int y) {
		if ((Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==5)&& Hero.getNombreVie()<3){
			System.out.println("Vous avez gagne	 une vie");
			Hero.ajoutVie();
			PacmanPainter.retireObjet(x,y);
		}	
	}

	public static void verifRetireMur(int x, int y) { // parametre : abscisse et ordonnee du heros
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==6) {
			PacmanPainter.retireObjet(x+1,y); // retire les 4 murs autour de la case retire mur
			PacmanPainter.retireObjet(x-1,y);
			PacmanPainter.retireObjet(x,y+1);
			PacmanPainter.retireObjet(x,y-1);
		}
	}
	
	public static void verifMonster(int abscisseHeros, int ordonneeHeros,int abscisseMonster, int ordonneeMonster) {
		// Ce programme permet de verifier si le heros est sur la meme case que le monstre, si oui il renvoie True
		if (abscisseHeros==abscisseMonster && ordonneeHeros==ordonneeMonster){
			if(Hero.isPotionEnCours()) {
				Monster.tueMonstre();
			}
			else {
				Hero.retireVie();
			}
			
		}
	}
	
	public static void verifPotion(int x, int y) { // parametre : abscisse et ordonnee du heros
		if (Integer.parseInt(PacmanPainter.getLabyrinthe()[x][y])==7) {
			System.out.println("Vous avez une potion!");
			Hero.ajoutPotion();
			PacmanPainter.retireObjet(x,y);
		}
	}
	
	public static void utilisePotion() {
		if(Hero.isPotionEnCours()) {
			long tempsEcoulePotion = System.currentTimeMillis() - Hero.getTempsLancementPotion();
			System.out.println(tempsEcoulePotion);
			long tempsPotion= 5000; // 5 secondes
			if(tempsEcoulePotion<tempsPotion) {
				verifMonster(Hero.getAbscisse(),Hero.getOrdonnee(),Monster.getAbscisse(),Monster.getOrdonnee());
			}
			else {
				Hero.potionPlusValide();
			}
		}	
	}

	@Override
	public boolean finJeu() {
		if (finJeu) {
			return true;
		}
		return false;
	}
	
}
