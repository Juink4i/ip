package Jkbot.command;

import Jkbot.exception.JkBotException;
import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;


/**
 * Command for unrecognized input.
 */
public class UnknownCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        String message = "Unrecognised command. Try again\n";
        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
