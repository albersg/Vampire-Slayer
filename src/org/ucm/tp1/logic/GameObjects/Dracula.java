package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire {
	private static boolean vampireAlive = false;

	public Dracula(Game game, int x, int y) {
		super(game, x, y);
		vampireAlive = true;
		icon = "D";
	}

	public static boolean getDraculaState() {
		return vampireAlive;
	}

	public static void draculaDies() {
		vampireAlive = false;
	}

	public void attack() {
		if (inGame()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null)
				other.receiveDraculaAttack();
		}
	}

	public boolean isDead() {
		if (health <= 0 || x >= game.getDimX()) {
			vampiresInGame--;
			return true;
		}
		return false;

	}

	public boolean receiveSlayerAttack(int damage) {
		health = health - damage;
		if (health <= 0)
			vampireAlive = false;
		return true;
	}

	public boolean receiveLightFlash() {
		return false;
	}

	public boolean receiveGarlicPush() {
		if (game.positionIsEmpty(x + 1, y) && x + 1 >= game.getDimX()) {
			vampireAlive = false;
		}
		return super.receiveGarlicPush();
	}
}
