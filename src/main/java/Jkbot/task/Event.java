package Jkbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String rawStartTime, String rawEndTime) {
        super(description);

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(rawStartTime.trim(), inputFormatter);
            this.endTime = LocalDateTime.parse(rawEndTime.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String newFormatStartTime = startTime.format(outputFormatter);
        String newFormatEndTime = endTime.format(outputFormatter);
        return "[E]" + super.toString() + " (from: " + newFormatStartTime + " to: " + newFormatEndTime + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime.format(fileFormatter) + " | " + endTime.format(fileFormatter);
    }
}
