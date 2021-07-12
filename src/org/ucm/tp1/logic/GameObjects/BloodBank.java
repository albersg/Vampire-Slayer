package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {
	public static final int BANK_HEALTH = 1;
	
	private int cost;
	
	public BloodBank (Game game, int x, int y, int z) {
		super(game,x,y);
		icon = "B";
		cost = z;
		health = BANK_HEALTH;
	}

	@Override
	public void attack() {}

	@Override
	protected void advanceOnePosition() {
		game.givePlayerTenPercent(cost);
	}

	@Override
	protected boolean isDead() {
		return !inGame;
	}
	
	public boolean receiveVampireAttack(int damage) {
		inGame = false;
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		inGame = false;
		return true;
	}
	
	@Override
	protected String serialize() {
		return icon + ";" + x + ";" + y + ";" + health + ";" + cost + "%n";
	}
	
	// Se redefine el toString en esta clase ya que se tiene que mostrar el coste en vez de la salud,
	// y además para evitar problemas con las variables en el serialize.
	public String toString() {
		return icon +  " [" + cost + "]";
	}

}
