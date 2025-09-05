package Jkbot.utils;

import java.util.Scanner;

/**
 * Handles all user interface interactions.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner;
    public static String LINE = "________________________________\n";
    public static String OPENING = LINE + "Hello! I'm NaiLongBot\nWhat can I do for you?\n" + LINE;
    public static String CLOSING = LINE + "Bye. Hope to see you again soon!" + "\n" + LINE;

    /**
     * Constructor.
     * initializes a new Scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out OPENING.
     */
    public void printWelcome() {
        System.out.println(OPENING);
    }

    public String getOPENING() {
        return OPENING;
    }

    /**
     * Prints out LINE.
     */
    public void printLine() {
        System.out.print(LINE);
    }

    /**
     * Prints out users input.
     *
     * @param msg the message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints out closing message.
     */
    public void printGoodbye() {
        printMessage(CLOSING);
    }
}
