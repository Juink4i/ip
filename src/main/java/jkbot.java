import java.util.ArrayList;
import java.util.Scanner;

public class jkbot {
    public static void main(String[] args) {
        String line = "_____________________________________________________________\n";
        String opening = line + "Hello! I'm jkbot\nWhat can I do for you?\n" + line;
        String closing = line + "Bye. Hope to see you again soon!" + "\n" + line;

        System.out.println(opening);
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> memory = new ArrayList<>();

        while (true) {
            System.out.print("input: ");
            String initText = scanner.nextLine().trim();

            // Skip empty input
            if (initText.isEmpty()) {
                continue;
            }

            String[] parts = initText.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "bye":
                    System.out.println(closing);
                    scanner.close();
                    return;

                case "mark":
                    if (parts.length > 1) {
                        try {
                            int taskIndex = Integer.parseInt(parts[1]) - 1;
                            if (taskIndex >= 0 && taskIndex < memory.size()) {
                                memory.get(taskIndex).doTask();
                                System.out.println(line +
                                        "Good job for completing!!!\n" +
                                        memory.get(taskIndex).toString() +
                                        "\n" +
                                        line);
                            } else {
                                System.out.println("Error: Invalid task number! Use 'list' to see available tasks.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a valid number!");
                        }
                    } else {
                        System.out.println("Error: Please specify a task number: mark [number]");
                    }
                    break;

                case "unmark":
                    if (parts.length > 1) {
                        try {
                            int taskIndex = Integer.parseInt(parts[1]) - 1;
                            if (taskIndex >= 0 && taskIndex < memory.size()) {
                                memory.get(taskIndex).UndoTask();
                                System.out.println(line +
                                        "You are undoing this task\n"+
                                        memory.get(taskIndex).toString() +
                                        "\n" +
                                        line);
                            } else {
                                System.out.println("Error: Invalid task number! Use 'list' to see available tasks.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a valid number!");
                        }
                    } else {
                        System.out.println("Error: Please specify a task number: unmark [number]");
                    }
                    break;

                case "todo":
                    if (parts.length > 1) {
                        String todoDescription = parts[1].trim();
                        if (!todoDescription.isEmpty()) {
                            Todo newTodo = new Todo(todoDescription);
                            memory.add(newTodo);
                            System.out.println(line);
                            System.out.println("Got it. I've added this task:\n" + newTodo.toString() +
                                    "\nNow you have " + memory.size() + " tasks in the list");
                            System.out.println(line);
                        } else {
                            System.out.println("Error: Todo description cannot be empty!");
                        }
                    } else {
                        System.out.println("Error: Please specify a todo description: todo [description]");
                    }
                    break;

                case "deadline":
                    if (parts.length > 1) {
                        String deadlineInfo = parts[1];
                        String[] dateParts = deadlineInfo.split("/by", 2);
                        if (dateParts.length == 2) {
                            String byDate = dateParts[1].trim();
                            String desc = dateParts[0];

                            if (!deadlineInfo.isEmpty()) {
                                Deadline newDeadline = new Deadline(desc, byDate);
                                memory.add(newDeadline);
                                System.out.println(line);
                                System.out.println("Got it. I've added this deadline:\n" + newDeadline.toString() +
                                        "\nNow you have " + memory.size() + " tasks in the list");
                            }

                            System.out.println(line + "added deadline: " + deadlineInfo + "\n" + line);
                        } else {
                            System.out.println("Error: Please specify deadline details: deadline [description] /by [time]");
                        }
                    }
                    break;

                case "event":
                    if (parts.length > 1) {
                        String eventInfo = parts[1];
                        String[] eventParts = eventInfo.split(" /from | /to ");

                        if (eventParts.length == 3) {
                            String desc = eventParts[0];
                            String startTime = eventParts[1].trim();
                            String endTime = eventParts[2].trim();

                            if (!eventInfo.isEmpty()) {
                                Event newEvent = new Event(desc, startTime, endTime);
                                memory.add(newEvent);
                                System.out.println(line);
                                System.out.println("Got it. I've added this deadline:\n" + newEvent.toString() +
                                        "\nNow you have " + memory.size() + " tasks in the list");
                            }

                            System.out.println(line + "added event: " + eventInfo + "\n" + line);
                        } else {
                            System.out.println("Error: Please specify event details: event [description] /from [start] /to [end]");
                        }
                    }
                    break;

                case "list":
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
                    break;

                default:
                    // Check if it's a simple task without command
                    System.out.println("Unrecognised command. Try again\n");
            }
        }
    }
}