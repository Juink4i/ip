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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        System.out.print(Ui.LINE);
        if (tasks.isEmpty()) {
            ui.printMessage("No tasks in your list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.printMessage((i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
