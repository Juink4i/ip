package Jkbot.tests;

import Jkbot.command.AddTodoCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AddTodoCommandTest {

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
    void executeAddsTodoAndPrintsMessage() throws JkBotException {
        AddTodoCommand command = new AddTodoCommand("Read book");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.getTask(0).getDesc());

        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this task"));
        assertTrue(output.contains("Read book"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddTodoCommand command = new AddTodoCommand("Read book");
        assertFalse(command.isExit());
    }
}
