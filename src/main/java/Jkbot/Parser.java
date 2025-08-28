package Jkbot;

import Jkbot.Command.AddDeadlineCommand;
import Jkbot.Command.AddEventCommand;
import Jkbot.Command.AddTodoCommand;
import Jkbot.Command.Command;
import Jkbot.Command.DeleteCommand;
import Jkbot.Command.ExitCommand;
import Jkbot.Command.ListCommand;
import Jkbot.Command.MarkCommand;
import Jkbot.Command.ShowCommand;
import Jkbot.Command.UnknownCommand;
import Jkbot.Command.UnmarkCommand;
import Jkbot.exception.JkBotException;
import Jkbot.exception.EmptyDescriptionException;

public class Parser {

    public static Command parse(String input) throws JkBotException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException("Empty command");
        }

        String[] parts = input.split(" ", 2);
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

        default:
            return new UnknownCommand();
        }
    }

    private static void validateNotEmpty(String arguments, String format) throws EmptyDescriptionException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("Error: Please specify - " + format);
        }
    }
}