package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to add a new deadline task.
 */
public class AddDeadlineCommand implements Command {
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addDeadline(input);
        ui.printMessage("Got it. I've added this deadline:\n" +
                tasks.getTask(tasks.size() - 1).toString() +
                "\nNow you have " + tasks.size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
