package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;
import org.ucm.tp1.logic.exceptions.InvalidTypeException;

public class AddVampireCommand extends Command {
	public static final String detailsMsg = String.format("[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
	public static final String helpMsg = String.format("add a vampire in position x, y");
	public static final String failedVampireMsg = String.format("[ERROR]: Failed to add this vampire");

	public AddVampireCommand() {
		super("vampire","v",detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addTypeVampire(vampireType,x,y);	
		}
		catch(InvalidTypeException exType) {
			System.out.format(exType.getMessage());
			throw new CommandExecuteException(detailsMsg);
		}
		catch(CommandExecuteException ex) {
			System.out.format(ex.getMessage() + "%n");
			throw new CommandExecuteException(failedVampireMsg);
		}
		return true;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length == 3) {
				vampireType = "";
				try {
					x = Integer.parseInt(words[1]);
					y = Integer.parseInt(words[2]);
				}
				catch(NumberFormatException ex) {
					throw new CommandParseException(failedParse + " for add " + name + " command, number expected: " + detailsMsg);
				}
			} else if (words.length == 4) {
				vampireType = words[1];
				try {
					x = Integer.parseInt(words[2]);
					y = Integer.parseInt(words[3]);
				}
				catch(NumberFormatException ex) {
					throw new CommandParseException(failedParse + " for add " + name + " command, number expected: " + detailsMsg);
				}
			} else
				throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			return this;
		}

		return null;
	}

}
