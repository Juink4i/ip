package Jkbot.command;

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

    public String getDateString() {
        return dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        String result = tasks.getTasksOnDate(dateString);
        System.out.print(Ui.LINE);
        ui.printMessage(result);
        System.out.print(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
