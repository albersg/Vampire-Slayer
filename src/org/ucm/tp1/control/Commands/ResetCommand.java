package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class ResetCommand extends Command {
	public static final String detailsMsg = String.format("[r]eset");
	public static final String helpMsg = String.format("reset game");

	public ResetCommand() {
		super("reset", "r", detailsMsg, helpMsg);

	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.initializeGame();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
