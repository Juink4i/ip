package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to add a new deadline task.
 */
public class AddDeadlineCommand implements Command {
    private String arguments;

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addDeadline(arguments);
        ui.printMessage("Got it. I've added this deadline:\n" +
                tasks.getTask(tasks.size() - 1).toString() +
                "\nNow you have " + tasks.size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
