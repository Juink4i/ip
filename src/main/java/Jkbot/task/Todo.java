package Jkbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public LocalDateTime getDate() {
        return LocalDateTime.MAX;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
