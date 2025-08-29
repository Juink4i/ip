package Jkbot.tests;

import Jkbot.command.AddEventCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AddEventCommandTest {

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
    void executeAddsEventAndPrintsMessage() throws JkBotException {
        String eventArgs = "Conference /from 28/8/2025 1000 /to 28/8/2025 1800";
        AddEventCommand command = new AddEventCommand(eventArgs);
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0).toString().contains("Conference"));
        assertTrue(taskList.getTask(0).toString().contains("Aug 28 2025 10:00 AM"));
        assertTrue(taskList.getTask(0).toString().contains("Aug 28 2025 6:00 PM"));

        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this event"));
        assertTrue(output.contains("Conference"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddEventCommand command = new AddEventCommand("Conference /from 28/8/2025 1000 /to 28/8/2025 1800");
        assertFalse(command.isExit());
    }
}
