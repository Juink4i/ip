package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to unmark a completed task.
 */
public class UnmarkCommand implements Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.unmarkTask(taskIndex);
        ui.printMessage("You are undoing this task\n" + tasks.getTask(taskIndex).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
