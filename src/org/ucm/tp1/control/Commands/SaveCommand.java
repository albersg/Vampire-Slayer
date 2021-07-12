package org.ucm.tp1.control.Commands;

import java.io.IOException;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class SaveCommand extends Command{
	public static final String detailsMsg = String.format("[S]ave <filename>");
	public static final String helpMsg = String.format("Save the state of the game to a file.");
	private String filename;
	

	public SaveCommand() {
		super("save", "S", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.saveGame(filename);
		} catch (IOException e) {
			System.out.format(e.getMessage() + "%n");
		}
		return false;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 2) 
				throw new CommandParseException("[ERROR]: " + incorrectNumberOfArgsMsg +
												" for " + name + " command: " + detailsMsg);
			filename = words[1];
			return this;
		}
		return null;
	}

}
