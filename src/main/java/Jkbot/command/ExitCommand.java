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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        System.out.print(Ui.LINE);
        storage.saveTasks(tasks.getTasks());
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
