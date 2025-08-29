package Jkbot.command;

import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.exception.JkBotException;

/**
 * Represents an executable command in the Jkbot application.
 */
public interface Command {
    /**
     * Executes the command with the given dependencies.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for displaying messages
     * @param storage the storage component for persistence
     * @throws JkBotException if command execution fails
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException;

    /**
     * Indicates whether this command should exit the application.
     *
     * @return true if this is an exit command, false otherwise
     */
    boolean isExit();
}
