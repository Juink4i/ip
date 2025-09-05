package Jkbot.command;

import Jkbot.command.DeleteCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for DeleteCommand Class
class DeleteCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeDeletesTaskAndReturnsMessage() throws JkBotException {
        DeleteCommand command = new DeleteCommand(0);
        String result = command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Task 2", taskList.getTask(0).getDesc());

        assertTrue(result.contains("I've removed this task"));
        assertTrue(result.contains("Task 1"));
        assertTrue(result.contains("Now you have 1 tasks"));
    }

    @Test
    void isExitReturnsFalse() {
        DeleteCommand command = new DeleteCommand(0);
        assertFalse(command.isExit());
    }
}
