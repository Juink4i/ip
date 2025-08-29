package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command for unrecognized input.
 */
public class UnknownCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        System.out.print(Ui.LINE);
        ui.printMessage("Unrecognised command. Try again");
        System.out.print(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
