package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to list all tasks.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            // ui.printMessage("No tasks in your list yet!");
            message = new StringBuilder("No tasks in your list yet!\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                // ui.printMessage((i + 1) + ". " + tasks.getTask(i).toString());
                message.append((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
            }
        }
        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
