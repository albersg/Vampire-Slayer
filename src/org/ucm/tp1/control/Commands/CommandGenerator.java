package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.exceptions.CommandParseException;

public class CommandGenerator {
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new BankBloodCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
			};

	public static Command parse(String[] commandWords) throws CommandParseException {
		for(Command c:availableCommands) {
			Command parsedCommand=c.parse(commandWords);
			if(parsedCommand!=null)
				return parsedCommand;
		}
		return null;
	}

	public static String commandHelp() {
		String helpMsg = "Available commands:\n";
		for(Command c:availableCommands) {
			helpMsg = helpMsg + c.helpText();
		}
		return helpMsg;
	}
}
