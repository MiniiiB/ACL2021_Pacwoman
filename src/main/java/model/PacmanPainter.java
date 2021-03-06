package model;
import start.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;


import engine.GamePainter;
import engine.Hero;
import java.nio.file.*;

//afficheur graphique pour le game

public class PacmanPainter implements GamePainter {
	//la taille de la fenetre
	protected static final int WIDTH = 400;
	protected static final int HEIGHT = 400;
	static String[][] labyrinthe=new String[40][40]; // retient le labyrinthe dans un tableau

	//appelle constructeur parent @param game le jeutest a afficher
	public PacmanPainter() {
	}
	//TEST MODIFICATION ELOISE
	//methode  redefinie de Afficheur retourne une image du jeu
	@Override	// override = fonction qui est deja dans la classe mere
	public void draw(BufferedImage im) { 
		Image key;
		Image player;
		Image wall;
		Image ground;
		Image start;
		Image finish;
		Image life;
		Image monster;
		Image caseSpe;
		Image potion;
		Image playerPotion;
		Image gameOver;
		Image win;
		Image tp;
		BufferedReader helpReader;
		Path cheminKey = Paths.get("src/assets/keyYellow.png");
		Path absoluKey = cheminKey.toAbsolutePath();
		Path cheminPlayer = Paths.get("src/assets/p3_front.png");
		Path absoluPlayer = cheminPlayer.toAbsolutePath();
		Path cheminWall = Paths.get("src/assets/boxAlt.png");
		Path absoluWall = cheminWall.toAbsolutePath();
		Path cheminGround = Paths.get("src/assets/ground.png");
		Path absoluGround = cheminGround.toAbsolutePath();
		Path cheminStart = Paths.get("src/assets/flagGreen.png");
		Path absoluStart= cheminStart.toAbsolutePath();	
		Path cheminFinish = Paths.get("src/assets/flagRed.png");
		Path absoluFinish = cheminFinish.toAbsolutePath();
		Path cheminLife = Paths.get("src/assets/heart.png");
		Path absoluLife = cheminLife.toAbsolutePath();
		Path cheminMonster = Paths.get("src/assets/monster.png");
		Path absoluMonster = cheminMonster.toAbsolutePath();
		Path cheminCaseSpe = Paths.get("src/assets/lock_yellow.png");
		Path absoluCaseSpe = cheminCaseSpe.toAbsolutePath();
		Path cheminPotion = Paths.get("src/assets/potion.png");
		Path absoluPotion = cheminPotion.toAbsolutePath();
		Path cheminPlayerPotion = Paths.get("src/assets/p2_front.png");
		Path absoluPlayerPotion = cheminPlayerPotion.toAbsolutePath();
		Path cheminGameOver = Paths.get("src/assets/gameover.png");
		Path absoluGameOver = cheminGameOver.toAbsolutePath();
		Path cheminWin = Paths.get("src/assets/win.png");
		Path absoluWin = cheminWin.toAbsolutePath();
		Path cheminTP = Paths.get("src/assets/tp.png");
		Path absoluTP = cheminTP.toAbsolutePath();
		//System.out.println("chemin absolu : " + absoluKey.toString());
		key = new ImageIcon(absoluKey.toString()).getImage();
		player = new ImageIcon(absoluPlayer.toString()).getImage();
		wall = new ImageIcon(absoluWall.toString()).getImage();
		ground = new ImageIcon(absoluGround.toString()).getImage();
		start = new ImageIcon(absoluStart.toString()).getImage();
		finish = new ImageIcon(absoluFinish.toString()).getImage();
		life = new ImageIcon(absoluLife.toString()).getImage();
		monster = new ImageIcon(absoluMonster.toString()).getImage();
		caseSpe = new ImageIcon(absoluCaseSpe.toString()).getImage();
		potion = new ImageIcon(absoluPotion.toString()).getImage();
		playerPotion = new ImageIcon(absoluPlayerPotion.toString()).getImage();
		gameOver = new ImageIcon(absoluGameOver.toString()).getImage();
		win = new ImageIcon(absoluWin.toString()).getImage();
		tp = new ImageIcon(absoluTP.toString()).getImage();
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		

		if (PacmanGame.finJeuVie) {
			/*crayon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			crayon.setColor(Color.BLACK);
			int x_fin[] = {0,0,400,400};
			int y_fin[] = {0,400,400,0};
			int n_fin = 4;
			crayon.fillPolygon(x_fin,y_fin,n_fin);
			crayon.setColor(Color.WHITE);
			crayon.drawString("Partie perdue: vous n'avez plus de vie",50,200);*/
			int xImage = 0;
			int yImage = 0;
			crayon.drawImage(gameOver, xImage, yImage, null);
		}
		else if (PacmanGame.finJeuTemps) {
			/*crayon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			crayon.setColor(Color.BLACK);
			int x_fin[] = {0,0,400,400};
			int y_fin[] = {0,400,400,0};
			int n_fin = 4;
			crayon.fillPolygon(x_fin,y_fin,n_fin);
			crayon.setColor(Color.WHITE);
			crayon.drawString("Partie perdue: le temps est ecoule",50,200);*/
			int xImage = 0;
			int yImage = 0;
			crayon.drawImage(gameOver, xImage, yImage, null);
		}
		else if (PacmanGame.finJeuVictoire) {
			/*crayon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			crayon.setColor(Color.BLACK);
			int x_fin[] = {0,0,400,400};
			int y_fin[] = {0,400,400,0};
			int n_fin = 4;
			crayon.fillPolygon(x_fin,y_fin,n_fin);
			crayon.setColor(Color.WHITE);
			crayon.drawString("Vous avez gagne",50,200);*/
			//Image
			int xImage = 0;
			int yImage = 0;
			crayon.drawImage(win, xImage, yImage, null);
		}
		else {
			try {
				helpReader = new BufferedReader(new FileReader("helpFilePacman.txt"));
				String ligne;
				
				int compteurLignes = 0;
				
				while ((ligne = helpReader.readLine()) != null) {
					String[] lignes = ligne.split("");
					//getLabyrinthe()[compteurLignes]=lignes;
						//On utilise des cases de 20px par 20 px
						for (int symboles = 0; symboles < lignes.length; symboles ++){
							//int x[] = {compteurLignes*20,compteurLignes*20,compteurLignes*20+20,compteurLignes*20+20};
							//int y[] = {symboles*20,symboles*20+20,symboles*20+20,symboles*20};
							int xImage = compteurLignes*20;
							int yImage = symboles*20;
							//int n = 4;
							int res = Integer.parseInt(labyrinthe[compteurLignes][symboles]);
							if (res == 0){ //Case libre
								crayon.drawImage(ground, xImage, yImage, null);
								//crayon.setColor(Color.lightGray);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 1){ //Mur
								crayon.drawImage(wall, xImage, yImage, null);
								//crayon.setColor(Color.lightGray);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 2){ //Depart
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(start, xImage, yImage, null);
								//crayon.setColor(Color.green);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 3){ //Arrivee
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(finish, xImage, yImage, null);
								//crayon.setColor(Color.red);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 4){ //Case cle
								//crayon.setColor(Color.ORANGE);
								//crayon.fillPolygon(x,y,n);
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(key, xImage, yImage, null);
							}
							
							if (res==5) { // Case vie
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(life, xImage, yImage, null);
							}
							
							if (res == 6){ //Case casser un mur
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(caseSpe, xImage, yImage, null);
							}
							if (res == 7){ //Case potion 
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(potion, xImage, yImage, null);
							}
							if (res == 8 || res == 9){ //Case TP 
								crayon.drawImage(tp, xImage, yImage, null);
							}
							
							//System.out.println(Arrays.toString(lignes));
							//System.out.println(Arrays.toString(x));
							//System.out.println(Arrays.toString(labyrinthe[10]));
							
						}
					compteurLignes ++;
				}		
			}
			catch (IOException e) {
				System.out.println("Help not available");
			}
			
			//On affiche la position des qu'elle change
			if (!Hero.isPotionEnCours()) {
				crayon.drawImage(player,(400*Hero.getAbscisse())/20, (400*Hero.getOrdonnee())/20, null);
			}
			else {
				crayon.drawImage(playerPotion,(400*Hero.getAbscisse())/20, (400*Hero.getOrdonnee())/20, null);
			}
			if(Main.monstre.isMonstreEnVie()) {
				crayon.drawImage(monster,(400*Main.monstre.getAbscisse())/20, (400*Main.monstre.getOrdonnee())/20, null);
			}
			if(Main.monstre2.isMonstreEnVie()) {
				crayon.drawImage(monster,(400*Main.monstre2.getAbscisse())/20, (400*Main.monstre2.getOrdonnee())/20, null);
			}
			//crayon.setColor(Color.pink);
			//crayon.fillOval((800*Hero.getAbscisse())/20,(800*Hero.getOrdonnee())/20,800/20,800/20);
			//System.out.println(Arrays.toString(labyrinthe[10]));
			if (Hero.getNombreVie() == 3) {
				crayon.drawImage(life,0, 0, null);
				crayon.drawImage(life,20, 0, null);
				crayon.drawImage(life,40, 0, null);
			}
			if (Hero.getNombreVie() == 2) {
				crayon.drawImage(life,0, 0, null);
				crayon.drawImage(life,20, 0, null);
			}
			if (Hero.getNombreVie() == 1) {
				crayon.drawImage(life,0, 0, null);
			}
			crayon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			crayon.setColor(Color.BLACK);
			int x_chrono[] = {340,340,360,360};
			int y_chrono[] = {0,20,20,0};
			int n_chrono = 4;
			crayon.fillPolygon(x_chrono,y_chrono,n_chrono);
			crayon.setColor(Color.WHITE);
			crayon.drawString(PacmanGame.gettime(),345,10);
		}
	}
	
	public void creationLabyrinthe(){ // cree le labyrinthe une seule fois
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader("helpFilePacman.txt"));
			String ligne;
			
			int compteurLignes = 0;
			
			while ((ligne = helpReader.readLine()) != null) {
				String[] lignes = ligne.split("");
				labyrinthe[compteurLignes]=lignes;
				compteurLignes ++;
			}
			//System.out.println(Arrays.toString(labyrinthe[10]));
		}
		catch (IOException e) {
			System.out.println("Help not available");
		}
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public static String[][] getLabyrinthe() {
		return labyrinthe;
	}
	
	public static void retireObjet(int abscisse, int ordonnee) { // retire la cle/potion du plateau quand elle est recuperee par le heros
		labyrinthe[abscisse][ordonnee]="0";
	}
	
	public static void ajoutArrivee(int abscisse, int ordonnee) {
		labyrinthe[abscisse][ordonnee]="3";
	}
 }
