package Jkbot.Command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to show tasks on a specific date.
 */
public class ShowCommand implements Command {
    private String dateString;

    public ShowCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        String result = tasks.getTasksOnDate(dateString);
        ui.printMessage(result);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
