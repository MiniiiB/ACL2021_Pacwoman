package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;


import engine.GamePainter;
import engine.Hero;
import engine.Monster;
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
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		

		if (PacmanGame.finJeu == true) {
			crayon.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			crayon.setColor(Color.BLACK);
			int x_fin[] = {0,0,400,400};
			int y_fin[] = {0,400,400,0};
			int n_fin = 4;
			crayon.fillPolygon(x_fin,y_fin,n_fin);
			crayon.setColor(Color.WHITE);
			crayon.drawString("Fin du jeu",150,240);
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
							int x[] = {compteurLignes*20,compteurLignes*20,compteurLignes*20+20,compteurLignes*20+20};
							int y[] = {symboles*20,symboles*20+20,symboles*20+20,symboles*20};
							int xImage = compteurLignes*20;
							int yImage = symboles*20;
							int n = 4;
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
								// thomas : j'ai mis une case noire pour la potion pour l'instant
								crayon.setColor(Color.BLACK);
								crayon.fillPolygon(x,y,n);
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
				// thomas : il me faut une image d'un monstre different pour montrer que la potion est en cours a ce moment la, pour l'instant j'ai remis le point rose
				crayon.setColor(Color.pink);
				crayon.fillOval((400*Hero.getAbscisse())/20,(400*Hero.getOrdonnee())/20,400/20,400/20);
			}
			if(Monster.isMonstreEnVie()) {
				crayon.drawImage(monster,(400*Monster.getAbscisse())/20, (400*Monster.getOrdonnee())/20, null);
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
	
	public static void retireObjet(int abscisse, int ordonnee) { // retire la cle du plateau quand elle est recuperee par le heros
		labyrinthe[abscisse][ordonnee]="0";
	}
}
