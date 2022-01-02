package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import engine.Monster;
public class Main 
{
	//On crée les monstres ici
	public static Monster monstre= new Monster(3,17);
	public static Monster monstre2= new Monster(18,1);
	
	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		// cree le labyrinthe une seule fois
		painter.creationLabyrinthe();
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}
}
