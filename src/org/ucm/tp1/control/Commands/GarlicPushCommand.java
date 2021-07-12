package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class GarlicPushCommand extends Command {
	public static final String detailsMsg = String.format("[g]arlic ");
	public static final String helpMsg = String.format( "pushes back vampires");
	public static final String failedGarlic = String.format("[ERROR]: Failed to garlic push");


	public GarlicPushCommand() {
		super("garlic", "g", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.doGarlicPush();
		}
		catch(CommandExecuteException ex) {
			System.out.format(ex.getMessage() + "%n");
			throw new CommandExecuteException(failedGarlic);
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}