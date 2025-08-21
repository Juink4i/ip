import java.util.ArrayList;
import java.util.Scanner;

public class jkbot {
    public static String line = "_____________________________________________________________\n";
    public static String opening = line + "Hello! I'm jkbot\nWhat can I do for you?\n" + line;
    public static String closing = line + "Bye. Hope to see you again soon!" + "\n" + line;

    public static ArrayList<Task> memory = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(opening);

        while (true) {
            try {
                System.out.print("input: ");
                String text = scanner.nextLine();

                if (text.isEmpty()) {
                    continue;
                }

                String[] parts = text.split(" ", 2);
                String command = parts[0].toLowerCase();

                // if detect "command"
                switch (command) {
                    case "bye":
                        handleBye();
                        return;

                    case "mark":
                        handleMark(parts);
                        break;

                    case "unmark":
                        handleUnmark(parts);
                        break;

                    case "todo":
                        handleTodo(parts);
                        break;

                    case "deadline":
                        handleDeadline(parts);
                        break;

                    case "event":
                        handleEvent(parts);
                        break;

                    case "list":
                        handleList();
                        break;

                    default:
                        handleDefault(text);
                }

            } catch (JkBotException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number");
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred");
            }
        }
    }

    private static void handleBye() {
        System.out.println(closing);
        scanner.close();
    }

    private static void handleMark(String[] parts) throws JkBotException {
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Error: Please specify a task number: mark [number]");
        }

        // the numbering in the list
        int taskIndex = Integer.parseInt(parts[1].trim()) - 1;

        if (taskIndex < 0 || taskIndex >= memory.size()) {
            throw new InvalidTaskNumberException("Error: Invalid task number! Use 'list' to see available tasks");
        }

        //marks completed
        memory.get(taskIndex).doTask();
        System.out.println(line +
                "Good job for completing!!!\n" +
                memory.get(taskIndex).toString() +
                "\n" +
                line);
    }

    private static void handleUnmark(String[] parts) throws JkBotException {
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Error: Please specify a task number: unmark [number]");
        }

        // the numbering in the list
        int taskIndex = Integer.parseInt(parts[1].trim()) - 1;

        if (taskIndex < 0 || taskIndex >= memory.size()) {
            throw new InvalidTaskNumberException("Error: Invalid task number! Use 'list' to see available tasks");
        }

        //marks uncompleted
        memory.get(taskIndex).undoTask();
        System.out.println(line +
                "You are undoing this task\n" +
                memory.get(taskIndex).toString() +
                "\n" +
                line);
    }

    private static void handleTodo(String[] parts) throws JkBotException {
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Error: Please specify a todo description: todo [description]");
        }

        String todoDescription = parts[1];
        if (todoDescription.isEmpty()) {
            throw new EmptyDescriptionException("Error: Todo description cannot be empty");
        }

        Todo newTodo = new Todo(todoDescription);
        memory.add(newTodo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" +
                            newTodo.toString() +
                            "\nNow you have " +
                            memory.size() +
                            " tasks in the list");
        System.out.println(line);
    }

    private static void handleDeadline(String[] parts) throws JkBotException {
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Error: Please specify deadline details - deadline [description] /by [time]");
        }

        String deadlineInfo = parts[1];
        String[] dateParts = deadlineInfo.split("/by", 2);

        if (dateParts.length != 2) {
            throw new InvalidFormatException("Error: Use format - deadline [description] /by [time]");
        }

        String description = dateParts[0];
        String byDate = dateParts[1];

        Deadline newDeadline = new Deadline(description, byDate);
        memory.add(newDeadline);
        System.out.println(line);
        System.out.println("Got it. I've added this deadline:\n" +
                            newDeadline.toString() +
                            "\nNow you have " + memory.size() +
                            " tasks in the list");
        System.out.println(line);
    }

    private static void handleEvent(String[] parts) throws JkBotException {
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Error: Please specify event details - event [description] /from [start] /to [end]");
        }

        String eventInfo = parts[1];
        String[] eventParts = eventInfo.split(" /from | /to ");

        if (eventParts.length != 3) {
            throw new InvalidFormatException("Error: Use format - event [description] /from [start] /to [end]");
        }

        String description = eventParts[0];
        String startTime = eventParts[1];
        String endTime = eventParts[2];

        Event newEvent = new Event(description, startTime, endTime);
        memory.add(newEvent);
        System.out.println(line);
        System.out.println("Got it. I've added this event:\n" +
                            newEvent.toString() +
                            "\nNow you have " +
                            memory.size() +
                            " tasks in the list");
        System.out.println(line);
    }

    private static void handleList() {
        System.out.println(line);

        if (memory.isEmpty()) {
            System.out.println("No tasks in your list yet!");
        } else {
            for (int i = 0; i < memory.size(); i++) {
                int index = i + 1;
                Task task = memory.get(i);
                System.out.println(index + ". " + task.toString());
            }
        }
        System.out.println(line);
    }

    private static void handleDefault(String text) {
        System.out.println("Unrecognised command. Try again\n");
    }
}