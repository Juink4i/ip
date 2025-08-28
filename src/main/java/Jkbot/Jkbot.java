package Jkbot;

import Jkbot.Command.Command;
import Jkbot.exception.JkBotException;

/**
 * The main chatbot application class that coordinates all components.
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

    /**
     * Starts the chatbot application and enters the main command loop.
     * Continuously reads user input, parses commands, and executes them
     * until the exit command ("bye") is received.
     */
    public void run() {
        ui.printWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input.isEmpty()) {
                    continue;
                }

                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();

            } catch (JkBotException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number");
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred");
            }
        }
    }

    /**
     * The main entry point of the Jkbot application.
     * Creates a new Jkbot instance and starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Jkbot().run();
    }
}