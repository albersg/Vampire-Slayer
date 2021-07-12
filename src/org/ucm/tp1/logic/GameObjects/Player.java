package org.ucm.tp1.logic.GameObjects;

import java.util.Random;

public class Player {
	public static final int STARTING_COINS = 50;
	
	private int numCoins;
	private Random rand;
	
	public Player(Random rand) {
		this.rand = rand;
		
		numCoins = STARTING_COINS;
	}

	public int getCoins() {
		return numCoins;
	}
	
	public void guessIfGetCoins() {
		if(rand.nextFloat() > 0.5) {
			numCoins = numCoins + 10;
		}
	}

	public void buyObject(int z) {
		numCoins = numCoins - z;
	}

	public boolean enoughCoins(int z) {
		return numCoins >= z;
	}

	public void addAmountOfCoins(int amount) {
		numCoins = numCoins + amount;
	}

	public void givePlayerTenPercent(int z) {
		numCoins = (int) (numCoins + z*0.10);
	}
	
	
}
