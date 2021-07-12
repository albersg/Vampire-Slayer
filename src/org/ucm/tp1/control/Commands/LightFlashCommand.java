package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class LightFlashCommand extends Command {
	public static final String detailsMsg = String.format("[l]ight");
	public static final String helpMsg = String.format("kills all the vampires");
	public static final String failedLight = String.format("[ERROR]: Failed to light flash");

	public LightFlashCommand() {
		super("light", "l", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.doLightFlash();
		}
		catch(CommandExecuteException ex){
			System.out.format(ex.getMessage() + "%n");
			throw new CommandExecuteException(failedLight);
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
