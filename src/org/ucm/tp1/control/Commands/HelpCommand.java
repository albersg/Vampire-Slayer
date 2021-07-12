package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class HelpCommand extends Command{
	public static final String detailsMsg = String.format("[h]elp");
	public static final String helpMsg = String.format("show this help");

	public HelpCommand() {
		super("help", "h", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException 	{
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
