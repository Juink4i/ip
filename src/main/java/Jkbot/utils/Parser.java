package Jkbot.utils;

import Jkbot.command.AddDeadlineCommand;
import Jkbot.command.AddEventCommand;
import Jkbot.command.AddTodoCommand;
import Jkbot.command.Command;
import Jkbot.command.DeleteCommand;
import Jkbot.command.ExitCommand;
import Jkbot.command.FindCommand;
import Jkbot.command.ListCommand;
import Jkbot.command.MarkCommand;
import Jkbot.command.ShowCommand;
import Jkbot.command.UnknownCommand;
import Jkbot.command.UnmarkCommand;
import Jkbot.exception.EmptyDescriptionException;
import Jkbot.exception.JkBotException;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into a specific {@link Command} that the bot can execute.
 * It reads the first word of the input to determine the type of command,
 * validates any required arguments, and then constructs the appropriate {@link Command} object
 * If the input does not match any known command, an {@link UnknownCommand}
 * is returned. If the input is empty or missing required arguments, and {@link EmptyDescriptionException} is thrown.
 *
 * This class also provides a private helper method {@code validateNotEmpty}
 * to ensure that arguments are not missing for commands that require them.
 *
 * @see Command
 * @see JkBotException
 * @see EmptyDescriptionException
 */
public class Parser {
    /**
     * Reads the users input based on the first word of input and executes the respective command
     *
     * @param input the users input
     * @return a specific Command to execute
     * @throws JkBotException if there is an error while parsing the input
     */
    public static Command parse(String input) throws JkBotException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException("Empty command");
        }

        String parts[] = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            validateNotEmpty(arguments, "mark [number]");
            return new MarkCommand(Integer.parseInt(arguments.trim()) - 1);

        case "unmark":
            validateNotEmpty(arguments, "unmark [number]");
            return new UnmarkCommand(Integer.parseInt(arguments.trim()) - 1);

        case "todo":
            validateNotEmpty(arguments, "todo [description]");
            return new AddTodoCommand(arguments);

        case "deadline":
            validateNotEmpty(arguments, "deadline [description] /by [time]");
            return new AddDeadlineCommand(arguments);

        case "event":
            validateNotEmpty(arguments, "event [description] /from [start] /to [end]");
            return new AddEventCommand(arguments);

        case "delete":
            validateNotEmpty(arguments, "delete [number]");
            return new DeleteCommand(Integer.parseInt(arguments.trim()) - 1);

        case "show":
            validateNotEmpty(arguments, "show d/M/yyyy");
            return new ShowCommand(arguments);

        case "find":
            validateNotEmpty(arguments, "find [key words]");
            return new FindCommand(arguments);

        default:
            return new UnknownCommand();
        }
    }

    /**
     * Reads the users input based on the first word of input and executes the respective command
     *
     * @param arguments the words after the command word in the input
     * @param format the supposed format for input based on the inputs first word
     * @throws EmptyDescriptionException if the description of the input is empty
     */
    private static void validateNotEmpty(String arguments, String format) throws EmptyDescriptionException {
        if (arguments.trim().isEmpty()) {
            System.out.print(Ui.LINE);
            throw new EmptyDescriptionException("Error: Please specify - " + format);
        }
    }
}