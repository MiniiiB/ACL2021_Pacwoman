package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import engine.GamePainter;
import engine.Hero;

//afficheur graphique pour le game

public class PacmanPainter implements GamePainter {
	//la taille de la fen�tre
	protected static final int WIDTH = 400;
	protected static final int HEIGHT = 400;
	static String[][] labyrinthe=new String[20][20]; // retient le labyrinthe dans un tableau

	//appelle constructeur parent @param game le jeutest a afficher
	public PacmanPainter() {
	}

	//methode  redefinie de Afficheur retourne une image du jeu
	@Override	// override = fonction qui est d�j� dans la classe m�re
	public void draw(BufferedImage im) { 
		BufferedReader helpReader;
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		try {
			helpReader = new BufferedReader(new FileReader("helpFilePacman.txt"));
			String ligne;
			
			int compteurLignes = 0;
			
			while ((ligne = helpReader.readLine()) != null) {
				String[] lignes = ligne.split("");
				getLabyrinthe()[compteurLignes]=lignes;
					//On utilise des cases de 20px par 20 px
					for (int symboles = 0; symboles < lignes.length; symboles ++){
						int x[] = {compteurLignes*20,compteurLignes*20,compteurLignes*20+20,compteurLignes*20+20};
						int y[] = {symboles*20,symboles*20+20,symboles*20+20,symboles*20};
						int n = 4;
						int res = Integer.parseInt(lignes[symboles]);
						if (res == 1){ //Mur
							crayon.setColor(Color.lightGray);
							crayon.fillPolygon(x,y,n);
						}
						if (res == 2){ //D�part
							crayon.setColor(Color.green);
							crayon.fillPolygon(x,y,n);
						}
						if (res == 3){ //Arriv�e
							crayon.setColor(Color.red);
							crayon.fillPolygon(x,y,n);
						}
						if (res == 4){ //Case cl�
							crayon.setColor(Color.ORANGE);
							crayon.fillPolygon(x,y,n);
						}
						//System.out.println(Arrays.toString(lignes));
						//System.out.println(Arrays.toString(x));
						//System.out.println(Arrays.toString(labyrinthe[1]));
						
					}
				compteurLignes ++;
			}
					
				}
		catch (IOException e) {
			System.out.println("Help not available");
		}
		
		//On affiche la position dès qu'elle change
		crayon.setColor(Color.pink);
		crayon.fillOval((400*Hero.getAbscisse())/20,(400*Hero.getOrdonnee())/20,400/20,400/20);
		
		
	}

	


/* 	public void drawPacman(BufferedImage im){	// dessiner le pacman � chaque instant ou chaque fois qu'il bouge
		boolean t = true;
		
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.yellow);
		crayon.fillOval(getWidth()/40,getHeight()/20,getWidth()/20,getHeight()/20);
		while (t) {
			crayon.setColor(Color.pink);
			crayon.fillOval((400*Hero.getAbscisse())/20,(400*Hero.getOrdonnee())/20,400/20,400/20);
		} */

	//crayon.setColor(Color.pink);
		//crayon.fillOval((400*abscisse)/20,(400*ordonnee)/20,400/20,400/20);
	//}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public static String[][] getLabyrinthe() {
		return labyrinthe;
	}

}
