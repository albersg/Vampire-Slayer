package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.exceptions.CommandParseException;

public class BankBloodCommand extends Command {
	public static final String detailsMsg = String.format("[b]ank <x> <y> <z>");
	public static final String helpMsg = String.format("add a blood bank with cost z in position x, y");
	
	public BankBloodCommand() {
		super("bank", "b", detailsMsg, helpMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.addBankBlood(x, y, z);
		return true;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 4) {
				throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);			}
			try {
				x = Integer.parseInt(words[1]);
				y = Integer.parseInt(words[2]);
				z = Integer.parseInt(words[3]);
			}
			catch(NumberFormatException ex) {
				throw new CommandParseException(failedParse + " for add " + name + " command, number expected: " + detailsMsg);
			}
			return this;
		}

		return null;
	}

}