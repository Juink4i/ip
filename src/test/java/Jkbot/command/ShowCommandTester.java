package Jkbot.tests;

import Jkbot.command.ShowCommand;
import Jkbot.utils.TaskList;
import Jkbot.utils.Ui;
import Jkbot.utils.Storage;
import Jkbot.task.Deadline;
import Jkbot.task.Event;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShowCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));

        // Add some tasks
        taskList.addTask(new Deadline("Submit report", "28/8/2025 2359"));
        taskList.addTask(new Event("Conference", "27/8/2025 1000", "29/8/2025 1800"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeShowsTasksOnGivenDate() throws Exception {
        ShowCommand command = new ShowCommand("28/8/2025");
        command.execute(taskList, ui, storage);

        String output = outContent.toString();
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Conference"));
    }

    @Test
    void executeShowsNoTasksMessageIfNone() throws Exception {
        ShowCommand command = new ShowCommand("1/1/2050");
        command.execute(taskList, ui, storage);

        String output = outContent.toString();
        assertTrue(output.contains("No tasks/events found on 1/1/2050"));
    }

    @Test
    void isExitReturnsFalse() {
        ShowCommand command = new ShowCommand("28/8/2025");
        assertFalse(command.isExit());
    }
}
