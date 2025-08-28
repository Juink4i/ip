package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;
import Jkbot.exception.InvalidTaskNumberException;

/**
 * Command to list all tasks.
 */
public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        if (tasks.isEmpty()) {
            ui.printMessage("No tasks in your list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.printMessage((i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
