package model;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


//controleur de type KeyListener
public class PacmanController implements GameController {
	//commande en cours
	private Cmd commandeEnCours;
	
	//construction du controleur par defaut le controleur n'a pas de commande
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	//quand on demande les commandes, le controleur retourne la commande en cours
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	//met a jour les commandes en fonctions des touches appuyees
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// si on appuie sur 'q',commande joueur est gauche
		case 37:
			this.commandeEnCours = Cmd.LEFT;
			break;
		case 38:
			this.commandeEnCours = Cmd.UP;
			break;
		case 39:
			this.commandeEnCours = Cmd.RIGHT;
			break;
		case 40:
			this.commandeEnCours = Cmd.DOWN;
			break;
		}
	}

	@Override
	//met a jour les commandes quand le joueur relache une touche
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	//ne fait rien		(pour moi=inutile)
	public void keyTyped(KeyEvent e) {
	}
}
