package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to mark a task as completed.
 */
public class MarkCommand implements Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.markTask(taskIndex);
        ui.printMessage("Good job for completing!!!\n" + tasks.getTask(taskIndex).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
