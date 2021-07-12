package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject {
	public static final int SLAYER_HEALTH = 3;
	
	public Slayer(Game game, int x , int y) {
		super(game,x,y);
		
		health = SLAYER_HEALTH;
		icon = "S";
	}

	public boolean isDead() {
		if(health <= 0) {
			return true;
		}
		return false;
	}
	
	public boolean receiveVampireAttack(int damage) {
		health = health - damage;
		if(health <= 0) {
			inGame = false;
		}
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		health = 0;
		inGame = false;
		return true;
	}

	@Override
	public void attack() {
		if(inGame()) {
			IAttack other;
			for(int i = x + 1; i < game.getDimX();i++) {
				other = game.getAttackableInPosition(i, y);
				if(other != null) {
					if(other.receiveSlayerAttack(1)) break;
				}
			}
		}
		
	}

	@Override
	protected void advanceOnePosition() {}

	@Override
	protected String serialize() {
		return icon + ";" + x + ";" + y + ";" + health + "%n";
	}

}
