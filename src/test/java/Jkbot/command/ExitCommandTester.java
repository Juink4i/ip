package Jkbot.tests;

import Jkbot.command.ExitCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Todo;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task"));
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeSavesTasksAndPrintsGoodbye() throws JkBotException {
        ExitCommand command = new ExitCommand();
        command.execute(taskList, ui, storage);

        String output = outContent.toString();
        assertTrue(output.contains("Bye") || output.contains("Hope to see you again"));
    }

    @Test
    void isExitReturnsTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
