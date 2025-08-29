package Jkbot.command;

import Jkbot.exception.JkBotException;
import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;

/**
 * Command to add a find word in desc.
 */
public class FindCommand implements Command{
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        int counter = 1;
        if (tasks.isEmpty()) {
            ui.printMessage("No tasks in your list yet!");
        } else {
            System.out.print(Ui.LINE);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTask(i).getDescription().contains(query)) {
                    ui.printMessage((counter) + ". " + tasks.getTask(i));
                    counter++;
                }
            }
        }
        System.out.print(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
