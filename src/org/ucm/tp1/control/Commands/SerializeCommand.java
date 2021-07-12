package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class SerializeCommand extends Command {
	public static final String detailsMsg = String.format("Seriali[z]e");
	public static final String helpMsg = String.format("Serializes the board.");

	public SerializeCommand() {
		super("serialize", "z", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.serializeCom();
		return false;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		return parseNoParamsCommand(words);
	}

}


