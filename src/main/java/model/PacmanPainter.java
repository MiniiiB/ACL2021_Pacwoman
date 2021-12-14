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
import java.nio.file.*;

//afficheur graphique pour le game

public class PacmanPainter implements GamePainter {
	//la taille de la fenetre
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 800;
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
		//System.out.println("chemin absolu : " + absoluKey.toString());
		key = new ImageIcon(absoluKey.toString()).getImage();
		player = new ImageIcon(absoluPlayer.toString()).getImage();
		wall = new ImageIcon(absoluWall.toString()).getImage();
		ground = new ImageIcon(absoluGround.toString()).getImage();
		start = new ImageIcon(absoluStart.toString()).getImage();
		finish = new ImageIcon(absoluFinish.toString()).getImage();
		life = new ImageIcon(absoluLife.toString()).getImage();
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		if (PacmanGame.finJeu == true) {
			crayon.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			crayon.setColor(Color.BLACK);
			crayon.drawString("Fin du jeu",400,400);
		}
		else {
			try {
				helpReader = new BufferedReader(new FileReader("helpFilePacman.txt"));
				String ligne;
				
				int compteurLignes = 0;
				
				while ((ligne = helpReader.readLine()) != null) {
					String[] lignes = ligne.split("");
					//getLabyrinthe()[compteurLignes]=lignes;
						//On utilise des cases de 40px par 40 px
						for (int symboles = 0; symboles < lignes.length; symboles ++){
							int x[] = {compteurLignes*40,compteurLignes*40,compteurLignes*40+40,compteurLignes*40+40};
							int y[] = {symboles*40,symboles*40+40,symboles*40+40,symboles*40};
							int xImage = compteurLignes*40;
							int yImage = symboles*40;
							int n = 4;
							int res = Integer.parseInt(lignes[symboles]);
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
			crayon.drawImage(player,(800*Hero.getAbscisse())/20, (800*Hero.getOrdonnee())/20, null);
			//crayon.setColor(Color.pink);
			//crayon.fillOval((800*Hero.getAbscisse())/20,(800*Hero.getOrdonnee())/20,800/20,800/20);
			//System.out.println(Arrays.toString(labyrinthe[10]));
		}
	}
	
	public void creationLabyrinthe(){
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
	
	public static void retireClePlateau(int abscisse, int ordonnee) { // retire la cle du plateau quand elle est recuperee par le heros
		labyrinthe[abscisse][ordonnee]="0";
	}
	
	public static void retirePdvPlateau(int abscisse, int ordonnee) { // retire le point de vie du plateau quand elle il est recupere par le heros
		labyrinthe[abscisse][ordonnee]="0";
	}
	
	public static void retireMursPlateau(int abscisse, int ordonnee) {
		System.out.println(Arrays.toString(labyrinthe[10]));
		labyrinthe[abscisse+1][ordonnee]="0";
		labyrinthe[abscisse-1][ordonnee]="0";
		labyrinthe[abscisse][ordonnee+1]="0";
		labyrinthe[abscisse][ordonnee-1]="0";
		System.out.println(Arrays.toString(labyrinthe[10]));
	}
}
