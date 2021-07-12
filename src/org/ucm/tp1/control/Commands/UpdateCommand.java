package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class UpdateCommand extends Command {
	public static final String detailsMsg = String.format("[n]one | []");
	public static final String helpMsg = String.format("update");

	public UpdateCommand() {
		super("none", "n", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.instructionNone();
		return true;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0]) || words[0].equals("")) {
			if (words.length != 1) {
				throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);			}
			return this;
		}

		return null;
	}


}
