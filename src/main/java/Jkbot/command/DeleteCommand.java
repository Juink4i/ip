package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;
import Jkbot.task.Task;

/**
 * Command to delete a task.
 */
public class DeleteCommand implements Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getIndex() {
        return taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        Task removedTask = tasks.deleteTask(taskIndex);
        System.out.print(Ui.LINE);
        ui.printMessage("I've removed this task:\n" + removedTask.toString() +
                "\nNow you have " + tasks.size() + " tasks in the list");
        System.out.print(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}