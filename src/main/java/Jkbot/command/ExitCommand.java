package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to exit the application and save tasks.
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        storage.saveTasks(tasks.getTasks());
        return Ui.CLOSING;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
