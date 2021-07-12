package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class AddCommand extends Command {
	public static final String detailsMsg = String.format("[a]dd <x> <y>");
	public static final String helpMsg = String.format("add a slayer in position x, y");
	public static final String failedAdd = String.format("[ERROR]: Failed to add slayer");
	
	public AddCommand() {
		super("add", "a", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.instructionAdd(x, y);
		}
		catch(CommandExecuteException ex) {
			System.out.format(ex.getMessage() + "%n");
			throw new CommandExecuteException(failedAdd);
		}
		return true;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 3) 
				throw new CommandParseException("[ERROR]: " + incorrectNumberOfArgsMsg +
												" for " + name + " command: " + detailsMsg);
			try {
				x = Integer.parseInt(words[1]);
				y = Integer.parseInt(words[2]);
			}
			catch(NumberFormatException ex) {
				throw new CommandParseException(failedParse + " for " + name + " slayer command, number expected: " + detailsMsg);
			}
			return this;
		}

		return null;
	}

}
