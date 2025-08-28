package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command for unrecognized input.
 */
public class UnknownCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        ui.printMessage("Unrecognised command. Try again\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
