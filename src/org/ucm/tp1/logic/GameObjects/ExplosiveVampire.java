package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire{

	public ExplosiveVampire(Game game, int x, int y) {
		super(game, x, y);
		
		icon = "EV";
	}

	public boolean receiveSlayerAttack(int damage) {
		health = health - damage;
		if (health <= 0) {
			inGame = false;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 || j != 0) {
						IAttack other = game.getAttackableInPosition(x + i, y + j);
						if (other != null)
							other.receiveSlayerAttack(1);
					}

				}
			}
		}
		return true;
	}

}
