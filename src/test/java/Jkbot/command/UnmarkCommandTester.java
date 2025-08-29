package Jkbot.tests;

import Jkbot.command.UnmarkCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import Jkbot.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UnmarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws InvalidTaskNumberException {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task"));

        // Mark the task first to test unmarking
        taskList.markTask(0);

        ui = new Ui();
        System.setOut(new PrintStream(outContent));

        storage = new Storage();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeUnmarksTaskAndPrintsMessage() throws JkBotException {
        UnmarkCommand command = new UnmarkCommand(0);
        command.execute(taskList, ui, storage);

        assertFalse(taskList.getTask(0).isDone());
        String output = outContent.toString();
        assertTrue(output.contains("You are undoing this task"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void isExitReturnsFalse() {
        UnmarkCommand command = new UnmarkCommand(0);
        assertFalse(command.isExit());
    }
}
