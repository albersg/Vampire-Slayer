package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class SuperCoinsCommand extends Command {
	public static final String detailsMsg = String.format("[c]oins");
	public static final String helpMsg = String.format("add 1000 coins");
	private static final int AMOUNT_COINS = 1000;

	public SuperCoinsCommand() {
		super("coins", "c", detailsMsg, helpMsg);

	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.addAmountOfCoins(AMOUNT_COINS);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
