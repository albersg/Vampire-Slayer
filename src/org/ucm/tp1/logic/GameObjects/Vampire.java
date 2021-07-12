package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject {
	public static final int VAMPIRE_HEALTH = 5;
	public static final int COUNTER_MOVE = 1;

	private static int remainingVampires;
	protected static int vampiresInGame;
	private static int startingVampires;
	private static boolean vampireArrived = false;
	protected int moveCounter = COUNTER_MOVE;

	public Vampire(Game game, int x, int y) {
		super(game, x, y);

		icon = "V";
		health = VAMPIRE_HEALTH;
		remainingVampires--;
		vampiresInGame++;
	}

	public static boolean checkIfVampireArrived() {
		return vampireArrived;
	}

	public static void vampireHasArrived() {
		vampireArrived = true;
	}

	public static int getVampiresInGame() {
		return vampiresInGame;
	}

	public static int getVampiresSpawned() {
		return startingVampires - remainingVampires;
	}

	public static int getRemainingVampires() {
		return remainingVampires;
	}

	public static boolean allVampiresDead() {
		return remainingVampires + vampiresInGame == 0;
	}

	public static void setNumberVampires(int vampires) {
		remainingVampires = vampires;
		startingVampires = vampires;
		vampiresInGame = 0;
	}

	public int getIfAdvance() {
		return moveCounter;
	}

	public boolean receiveSlayerAttack(int damage) {
		health = health - damage;
		return true;
	}

	public void attack() {
		if (inGame()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null)
				other.receiveVampireAttack(1);
		}
	}

	public void advanceOnePosition() {
		if (moveCounter <= 0) {
			if (game.positionIsEmpty(x - 1, y)) {
				moveCounter = COUNTER_MOVE + 1;
				x--;
				if (x <= -1)
					vampireHasArrived();
			}
		}
		moveCounter--;
	}

	public static void reset(int vampires) {
		setNumberVampires(vampires);
		vampiresInGame = 0;
		vampireArrived = false;
	}

	@Override
	public boolean isDead() {
		if (health <= 0 || x >= game.getDimX()) {
			inGame = false;
			vampiresInGame--;
			return true;
		}
		return false;
	}

	public boolean receiveGarlicPush() {
		moveCounter = COUNTER_MOVE;
		if (game.positionIsEmpty(x + 1, y)) {
			x = x + 1;
			if (x >= game.getDimX()) {
				inGame = false;
			}
			return true;
		}
		return false;
	}

	public boolean receiveLightFlash() {
		health = 0;
		return true;
	}

	@Override
	protected String serialize() {
		return icon + ";" + x + ";" + y + ";" + health + ";" + moveCounter + "%n";
	}

}
