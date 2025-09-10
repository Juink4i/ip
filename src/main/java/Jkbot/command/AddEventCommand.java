package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Command to add a new event task.
 */
public class AddEventCommand implements Command {
    private String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addEvent(arguments);
        storage.saveTasks(tasks.getTasks());

        String message = "Got it. I've added this event:\n" +
                tasks.getTask(tasks.size() - 1).toString() +
                "\nNow you have " + tasks.size() + " tasks in the list\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
