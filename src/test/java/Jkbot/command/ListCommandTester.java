package Jkbot.tests;

import Jkbot.command.ListCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executePrintsMessageWhenTaskListEmpty() throws JkBotException {
        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        String output = outContent.toString();
        assertTrue(output.contains("No tasks in your list yet!"));
    }

    @Test
    void executePrintsAllTasks() throws JkBotException {
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));

        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        String output = outContent.toString();
        assertTrue(output.contains("1. [T][ ] Task 1") || output.contains("1. Task 1"));
        assertTrue(output.contains("2. [T][ ] Task 2") || output.contains("2. Task 2"));
    }

    @Test
    void isExitReturnsFalse() {
        ListCommand command = new ListCommand();
        assertFalse(command.isExit());
    }
}
