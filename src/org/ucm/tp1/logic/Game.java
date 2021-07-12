package org.ucm.tp1.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.ucm.tp1.logic.GameObjects.BloodBank;
import org.ucm.tp1.logic.GameObjects.Dracula;
import org.ucm.tp1.logic.GameObjects.ExplosiveVampire;
import org.ucm.tp1.logic.GameObjects.GameObject;
import org.ucm.tp1.logic.GameObjects.IAttack;
import org.ucm.tp1.logic.GameObjects.Player;
import org.ucm.tp1.logic.GameObjects.Slayer;
import org.ucm.tp1.logic.GameObjects.Vampire;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.DraculaIsAliveException;
import org.ucm.tp1.logic.exceptions.InvalidPositionException;
import org.ucm.tp1.logic.exceptions.InvalidTypeException;
import org.ucm.tp1.logic.exceptions.NoMoreVampiresException;
import org.ucm.tp1.logic.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.view.IPrintable;

public class Game implements IPrintable {
	private Level level;
	private int cycleCounter;

	private Player player;
	private GamePrinter printer;
	private GameObjectBoard objectBoard;
	private boolean userExit = false;
	Random rand;

	public static final int SLAYER_COST = 50;
	private static final int GARLIC_COST = 10;
	public static final int FLASH_COST = 50;

	public static final String version = "3.0";
	public static final String welcomeMsg = String.format("Buffy the Vampire Slayer v" + version + "%n");
	public static final String numberCyclesMsg = String.format("Number of cycles: ");
	public static final String coinsMsg = String.format("Coins: ");
	public static final String remainingVampiresMsg = String.format("Remaining vampires: ");
	public static final String vampiresOnBoardMsg = String.format("Vampires on the board: ");
	public static final String executingCommand = String.format("[DEBUG] Executing: ");
	public static final String draculaIsAliveMsg = String.format("Dracula is alive");
	public static final String draculaOnBoardMsg = String.format("Dracula is already on board");
	public static final String invalidTypeMsg = String.format("[ERROR]: Unvalid type: ");
	public static final String invalidPositionMsg = String.format("Unvalid position");
	public static final String notRemainingVampiresMsg = String.format("[ERROR]: No more remaining vampires left");
	public static final String notEnoughCoinsMsg = String.format("Not enough coins");
	public static final String playerWinsMsg = String.format("Player wins");
	public static final String vampiresWinsMsg = String.format("Vampires win!");
	public static final String nobodyWinsMsg = String.format("Nobody wins...");

	public Game(Long seed, Level level) {
		this.level = level;

		rand = new Random(seed);
		initializeGame();

	}

	public String getPositionToString(int i, int j) {
		String aux = "";
		if (!objectBoard.positionIsEmpty(i, j))
			aux = objectBoard.getObjectInPosition(i, j).toString();

		return aux;
	}

	public String getWinnerMessage() {
		if (Vampire.checkIfVampireArrived()) {
			return String.format(vampiresWinsMsg);
		} else if (Vampire.allVampiresDead()) {
			return String.format(playerWinsMsg);
		} else
			return String.format(nobodyWinsMsg);
	}

	public String getInfo() {
		String info;
		info = String.format(numberCyclesMsg + cycleCounter + "%n" + coinsMsg + player.getCoins() + "%n"
				+ remainingVampiresMsg + Vampire.getRemainingVampires() + "%n" + vampiresOnBoardMsg
				+ Vampire.getVampiresInGame() + "%n");
		if (Dracula.getDraculaState())
			info = String.format(info + draculaIsAliveMsg + "%n");

		return info;
	}

	public String toString() {
		return printer.toString();
	}

	public void addNewVampires() {
		addNormalVampire();
		addDracula();
		addExplosiveVampire();

	}

	public void addNewVampireInPos(int x, int y, GameObject vampire) {
		objectBoard.addNewObject(vampire);
	}

	public boolean isPosibleAddVampire() {
		return Vampire.getVampiresSpawned() < level.getNumberOfVampires()
				&& rand.nextDouble() < level.getVampireFrequency();
	}

	public void addExplosiveVampire() {
		if (isPosibleAddVampire()) {
			int y = rand.nextInt(level.getDimY());
			if (objectBoard.positionIsEmpty(level.getDimX() - 1, y)) {
				objectBoard.addNewObject(new ExplosiveVampire(this, level.getDimX() - 1, y));
			}
		}

	}

	public void addDracula() {
		if (!Dracula.getDraculaState()) {
			if (isPosibleAddVampire()) {
				int y = rand.nextInt(level.getDimY());
				if (objectBoard.positionIsEmpty(level.getDimX() - 1, y)) {
					objectBoard.addNewObject(new Dracula(this, level.getDimX() - 1, y));
				}

			}
		}
	}

	public void addNormalVampire() {
		if (isPosibleAddVampire()) {
			int y = rand.nextInt(level.getDimY());
			if (objectBoard.positionIsEmpty(level.getDimX() - 1, y)) {
				objectBoard.addNewObject(new Vampire(this, level.getDimX() - 1, y));
			}

		}
	}

	public void setStartingVampires() {
		Vampire.setNumberVampires(level.getNumberOfVampires());
	}

	public void buySlayer() {
		player.buyObject(SLAYER_COST);
	}

	public int getDimX() {
		return level.getDimX();
	}

	public void attack() {
		objectBoard.attack();
	}

	public void removeDeadObjects() {
		objectBoard.removeDeadObjects();

	}

	public boolean validPosition(int x, int y) {
		return (x <= level.getDimX() - 1 && x >= 0 && y < level.getDimY() && y >= 0)
				&& objectBoard.positionIsEmpty(x, y);
	}

	public boolean isFinished() {
		return Vampire.checkIfVampireArrived() || Vampire.allVampiresDead() || userExit;
	}

	public void initializeGame() {
		cycleCounter = 0;
		Vampire.reset(level.getNumberOfVampires());
		Dracula.draculaDies();
		player = new Player(rand);
		printer = new GamePrinter(this, level.getDimX(), level.getDimY());
		objectBoard = new GameObjectBoard();

	}

	public void newCycle() {
		cycleCounter++;
	}

	public void instructionAdd(int x, int y) throws InvalidPositionException, NotEnoughCoinsException {
		if (!validPosition(x, y))
			throw new InvalidPositionException(
					String.format("[ERROR]: Position (" + x + ", " + y + "): " + invalidPositionMsg));
		else if (!player.enoughCoins(SLAYER_COST))
			throw new NotEnoughCoinsException(
					String.format("[ERROR]: Defender cost is " + SLAYER_COST + ": " + notEnoughCoinsMsg));
		objectBoard.addNewObject(new Slayer(this, x, y));
		player.buyObject(SLAYER_COST);
		instructionNone();
	}

	public void instructionNone() {
		player.guessIfGetCoins();
		objectBoard.advanceVampires();
		attack();
		addNewVampires();
		removeDeadObjects();
	}

	public void doExit() {
		userExit = true;
	}

	public IAttack getAttackableInPosition(int i, int y) {
		return objectBoard.getAttackableInPosition(i, y);
	}

	public boolean positionIsEmpty(int i, int y) {
		return objectBoard.positionIsEmpty(i, y);
	}

	public void addAmountOfCoins(int numCoins) {
		player.addAmountOfCoins(numCoins);
	}

	public void doLightFlash() throws NotEnoughCoinsException {
		if (!player.enoughCoins(FLASH_COST))
			throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is " + FLASH_COST + ": " + notEnoughCoinsMsg);

		objectBoard.doLightFlash();
		player.buyObject(FLASH_COST);
		instructionNone();

	}

	public void doGarlicPush() throws NotEnoughCoinsException {
		if (!player.enoughCoins(GARLIC_COST))
			throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is " + GARLIC_COST + ": " + notEnoughCoinsMsg);
		objectBoard.doGarlicPush();
		player.buyObject(GARLIC_COST);
		instructionNone();

	}

	public void addBankBlood(int x, int y, int z) throws InvalidPositionException, NotEnoughCoinsException {
		if (!validPosition(x, y))
			throw new InvalidPositionException(
					String.format("[ERROR]: Position (" + x + ", " + y + "): " + invalidPositionMsg));
		else if (!player.enoughCoins(z))
			throw new NotEnoughCoinsException(
					String.format("[ERROR]: Max Bank Blood cost is " + player.getCoins() + ": " + notEnoughCoinsMsg));
		objectBoard.addNewObject(new BloodBank(this, x, y, z));
		player.buyObject(z);
		instructionNone();
	}

	public void givePlayerTenPercent(int z) {
		player.givePlayerTenPercent(z);
	}

	public void addDraculaInPos(int x, int y) throws DraculaIsAliveException {
		if (Dracula.getDraculaState())
			throw new DraculaIsAliveException("[ERROR]: " + draculaOnBoardMsg);
		addNewVampireInPos(x, y, new Dracula(this, x, y));
	}

	public void addTypeVampire(String vampireType, int x, int y) throws CommandExecuteException {
		if (Vampire.getRemainingVampires() <= 0)
			throw new NoMoreVampiresException(notRemainingVampiresMsg);
		else if (!validPosition(x, y))
			throw new InvalidPositionException(
					String.format("[ERROR]: Position (" + x + ", " + y + "): " + invalidPositionMsg));
		else if (!(vampireType.toLowerCase().equals("e") || vampireType.toLowerCase().equals("d")
				|| vampireType.toLowerCase().equals("")))
			throw new InvalidTypeException(invalidTypeMsg);

		if (vampireType.toLowerCase().equals("e"))
			addNewVampireInPos(x, y, new ExplosiveVampire(this, x, y));

		else if (vampireType.toLowerCase().equals("d"))
			addDraculaInPos(x, y);

		else if (vampireType.toLowerCase().equals(""))
			addNewVampireInPos(x, y, new Vampire(this, x, y));
	}

	public boolean passTurn(String parameter) {
		return !(parameter.equals("r") || parameter.equals("reset") || parameter.equals("v")
				|| parameter.equals("vampire") || parameter.equals("c") || parameter.equals("coins"));
	}

	public String serialize() {
		return String.format("Cycles: " + cycleCounter + "%n" + coinsMsg + player.getCoins() + "%n" + "Level: "
				+ level.getName().toUpperCase() + "%n" + "Remaining Vampires: " + Vampire.getRemainingVampires() + "%n"
				+ "Vampires on Board: " + Vampire.getVampiresInGame() + "%n%n" + "Game Object List: " + "%n"
				+ objectBoard.serialize() + "%n");
	}

	public void saveGame(String filename) throws IOException {
		try (BufferedWriter myWriter = new BufferedWriter(
				new FileWriter("C:\\hlocal\\exTP\\comprobacionEntrega2.2\\" + filename + ".dat"))) {
			myWriter.write(welcomeMsg);
			myWriter.newLine();
			myWriter.write(serialize());
			System.out.println("Game successfully saved to file " + filename + ".dat.");

		}
	}

	public void serializeCom() {
		System.out.println(serialize());
	}

}