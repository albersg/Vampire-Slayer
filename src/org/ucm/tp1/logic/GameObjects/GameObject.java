package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack {
	protected String icon;
	protected int x, y, z;
	protected int health;
	protected Game game;
	protected boolean inGame = true;
	
	public GameObject(Game game, int x , int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	protected boolean isInPosition(int i, int j) {
		return x == i && y == j;
	}

	protected abstract void advanceOnePosition();
	
	
	protected abstract boolean isDead();
	
	public String toString() {
		return icon +  " [" + health + "]";
	}

	public boolean inGame() {
		return inGame;
	}

	protected abstract String serialize();
}
