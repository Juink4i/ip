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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.saveTasks(tasks.getTasks());
        String message;
        message = Ui.LINE +
                "I've removed this task:\n" + removedTask.toString() +
                "\nNow you have " + tasks.size() + " tasks in the list\n" +
                Ui.LINE;
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}