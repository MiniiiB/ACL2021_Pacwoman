package model;

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

//afficheur graphique pour le game

public class PacmanPainter implements GamePainter {
	//la taille de la fen�tre
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 800;
	static String[][] labyrinthe=new String[40][40]; // retient le labyrinthe dans un tableau

	//appelle constructeur parent @param game le jeutest a afficher
	public PacmanPainter() {
	}

	//methode  redefinie de Afficheur retourne une image du jeu
	@Override	// override = fonction qui est d�j� dans la classe m�re
	public void draw(BufferedImage im) { 
		Image key;
		Image player;
		Image wall;
		Image ground;
		Image start;
		Image finish;
		BufferedReader helpReader;
		key = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/keyYellow.png").getImage();
		player = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/p3_front.png").getImage();
		wall = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/boxAlt.png").getImage();
		ground = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/ground.png").getImage();
		start = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/flagGreen.png").getImage();
		finish = new ImageIcon("C:/Users/Thomas/Desktop/ACL2021_Pacwoman/src/assets/flagRed.png").getImage();
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
					getLabyrinthe()[compteurLignes]=lignes;
						//On utilise des cases de 40px par 40 px
						for (int symboles = 0; symboles < lignes.length; symboles ++){
							int x[] = {compteurLignes*40,compteurLignes*40,compteurLignes*40+40,compteurLignes*40+40};
							int y[] = {symboles*40,symboles*40+40,symboles*40+40,symboles*40};
							int xImage = compteurLignes*40;
							int yImage = symboles*40;
							int n = 4;
							int res = Integer.parseInt(lignes[symboles]);
							if (res == 0){ //Mur
								crayon.drawImage(ground, xImage, yImage, null);
								//crayon.setColor(Color.lightGray);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 1){ //Mur
								crayon.drawImage(wall, xImage, yImage, null);
								//crayon.setColor(Color.lightGray);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 2){ //D�part
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(start, xImage, yImage, null);
								//crayon.setColor(Color.green);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 3){ //Arriv�e
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(finish, xImage, yImage, null);
								//crayon.setColor(Color.red);
								//crayon.fillPolygon(x,y,n);
							}
							if (res == 4){ //Case cl�
								//crayon.setColor(Color.ORANGE);
								//crayon.fillPolygon(x,y,n);
								crayon.drawImage(ground, xImage, yImage, null);
								crayon.drawImage(key, xImage, yImage, null);
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
			crayon.drawImage(player,(800*Hero.getAbscisse())/20, (800*Hero.getOrdonnee())/20, null);
			//crayon.setColor(Color.pink);
			//crayon.fillOval((800*Hero.getAbscisse())/20,(800*Hero.getOrdonnee())/20,800/20,800/20);
		}
		
		
		
		
	}

	


/* 	public void drawPacman(BufferedImage im){	// dessiner le pacman � chaque instant ou chaque fois qu'il bouge
		boolean t = true;
		
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.yellow);
		crayon.fillOval(getWidth()/40,getHeight()/40,getWidth()/40,getHeight()/40);
		while (t) {
			crayon.setColor(Color.pink);
			crayon.fillOval((400*Hero.getAbscisse())/40,(400*Hero.getOrdonnee())/40,400/40,400/40);
		} */

	//crayon.setColor(Color.pink);
		//crayon.fillOval((400*abscisse)/40,(400*ordonnee)/40,400/40,400/40);
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
