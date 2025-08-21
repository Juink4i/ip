import java.util.ArrayList;
import java.util.Scanner;

public class jkbot {
    public static void main(String[] args) {
        String line = "_____________________________________________________________\n";
        String opening = line + "Hello! I'm jkbot\nWhat can I do for you?\n" + line;
        String closing = line + "Bye. Hope to see you again soon!" + "\n" + line;

        System.out.println(opening);
        Scanner scanner = new Scanner(System.in);

        ArrayList memory = new ArrayList();

        while (true) {
            System.out.print("input: ");
            String text = scanner.nextLine();
            // include storing text into a list
            memory.add(text);

            if (text.equalsIgnoreCase("bye")) {
                break;
            }

            if (text.equalsIgnoreCase("list")) {
                //display list
            }

            System.out.println(line + "added: " + text + "\n" + line);
        }

        System.out.println(closing);
        scanner.close();

    }
}
