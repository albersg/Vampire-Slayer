package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class ExitCommand extends Command {
	public static final String detailsMsg = String.format("[e]xit");
	public static final String helpMsg = String.format("exit game");

	public ExitCommand() {
		super("exit","e", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.doExit();		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}
	
}
