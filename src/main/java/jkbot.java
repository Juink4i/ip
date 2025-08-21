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
            String initText = scanner.nextLine();
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
                            int taskIndex = Integer.parseInt(parts[1]) - 1; // Convert to 0-based index
                            if (taskIndex >= 0 && taskIndex < memory.size()) {
                                memory.get(taskIndex).doTask();
                                System.out.println(line + "Good job for completing!!!\n"+ memory.get(taskIndex).getStatusIcon() + memory.get(taskIndex).getDesc() +"\n" + line);
                            } else {
                                System.out.println("Error: Invalid task number! Use 'list' to see available tasks.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a valid number!");
                        }
                    } else {
                        System.out.println("Error: Please specify a task number: complete [number]");
                    }
                    break;

                case "unmark":
                    if (parts.length > 1) {
                        try {
                            int taskIndex = Integer.parseInt(parts[1]) - 1; // Convert to 0-based index
                            if (taskIndex >= 0 && taskIndex < memory.size()) {
                                memory.get(taskIndex).UndoTask();
                                System.out.println(line + "You are undoing this task\n"+ memory.get(taskIndex).getStatusIcon() + memory.get(taskIndex).getDesc() +"\n" + line);
                            } else {
                                System.out.println("Error: Invalid task number! Use 'list' to see available tasks.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a valid number!");
                        }
                    } else {
                        System.out.println("Error: Please specify a task number: complete [number]");
                    }
                    break;

                case "list":
                    System.out.println(line);

                    for (int i = 0; i < memory.size(); i++) {
                        int index = i + 1;
                        String printIcon = memory.get(i).getStatusIcon();
                        String printDesc = memory.get(i).getDesc();
                        System.out.println(index + "."  + printIcon + " " + printDesc + "\n");
                    }
                    System.out.println(line);
                    break;

                default:
                    memory.add(new Task(initText));
                    System.out.println(line + "added: " + initText + "\n" + line);
            }
        }
    }
}
