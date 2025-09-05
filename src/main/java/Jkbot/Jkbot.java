package Jkbot;

import Jkbot.command.Command;
import Jkbot.exception.JkBotException;
import Jkbot.utils.Parser;
import Jkbot.utils.Storage;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;

/**
 * The main chatbot application class that coordinates all components.
 * Supports both console and GUI modes.
 */
public class Jkbot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Jkbot() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    public String executeCommand(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return Ui.LINE + "Something went wrong!\n" + e.getMessage() + "\n" + Ui.LINE;
        }
    }

    public Parser getParser() {
        return parser;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getResponse(String input) {
        return executeCommand(input);
    }
}
