package Jkbot.tests;

import Jkbot.command.MarkCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task"));  // Initially not done

        ui = new Ui();
        storage = new Storage();

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeMarksTaskAndPrintsMessage() throws JkBotException {
        MarkCommand command = new MarkCommand(0);
        command.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(0).isDone());
        String output = outContent.toString();
        assertTrue(output.contains("Good job for completing!!!"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void isExitReturnsFalse() {
        MarkCommand command = new MarkCommand(0);
        assertFalse(command.isExit());
    }
}
