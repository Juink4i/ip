package Jkbot.tests;

import Jkbot.command.AddDeadlineCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for AddDeadlineCommand Class
class AddDeadlineCommandTest {

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
    void executeAddsDeadlineAndPrintsMessage() throws JkBotException {
        String deadlineArgs = "Deadline Submit report /by 28/8/2025 2359";
        AddDeadlineCommand command = new AddDeadlineCommand(deadlineArgs);
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0).toString().contains("Submit report"));
        assertTrue(taskList.getTask(0).toString().contains("Aug 28 2025 11:59 PM"));

        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this deadline"));
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddDeadlineCommand command = new AddDeadlineCommand("Submit report /by 28/8/2025 2359");
        assertFalse(command.isExit());
    }
}
