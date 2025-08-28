package Jkbot.Command;

import Jkbot.Storage;
import Jkbot.TaskList;
import Jkbot.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to add a new event task.
 */
public class AddEventCommand implements Command {
    private String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addEvent(arguments);
        ui.printMessage("Got it. I've added this event:\n" +
                tasks.getTask(tasks.size() - 1).toString() +
                "\nNow you have " + tasks.size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
