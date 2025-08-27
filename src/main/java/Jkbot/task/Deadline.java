package Jkbot.task;

public class Deadline extends Task{
    String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    public void changeByDate(String newDate) {
        this.byDate = newDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.byDate + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate;
    }

}
