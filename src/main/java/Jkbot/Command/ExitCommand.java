package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to exit the application and save tasks.
 */
public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        storage.saveTasks(tasks.getTasks());
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
