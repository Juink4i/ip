package Jkbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime byDate;

    // Constructor parses raw String into LocalDateTime
    public Deadline(String description, String rawDate) {
        super(description);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.byDate = LocalDateTime.parse(rawDate.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    public LocalDateTime getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String newFormatByDate = byDate.format(outputFormatter);
        return "[D]" + super.toString() + "(by: " + newFormatByDate + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate.format(fileFormatter);
    }
}
