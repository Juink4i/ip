import java.util.ArrayList;
import java.util.Scanner;

public class jkbot {
    public static void main(String[] args) {
        String line = "_____________________________________________________________\n";
        String opening = line + "Hello! I'm jkbot\nWhat can I do for you?\n" + line;
        String closing = line + "Bye. Hope to see you again soon!" + "\n" + line;

        System.out.println(opening);
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> memory = new ArrayList<>();

        while (true) {
            System.out.print("input: ");
            String text = scanner.nextLine();

            if (text.equalsIgnoreCase("bye")) {
                break;
            }

            if (text.equalsIgnoreCase("list")) {
                System.out.println(line);
                for (int i = 0; i < memory.size(); i++) {
                    int index = i + 1;
                    String printText = memory.get(i);
                    System.out.println(index + ". " + printText + "\n");
                }
                System.out.println(line);
            } else {
                // include storing text into a list
                memory.add(text);
                System.out.println(line + "added: " + text + "\n" + line);
            }
        }

        System.out.println(closing);
        scanner.close();

    }
}
