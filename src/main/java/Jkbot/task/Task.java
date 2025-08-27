package Jkbot.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDesc() {
        return description;
    }

    public void doTask() {
        isDone = true;
    }

    public void undoTask() {
        isDone = false;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDesc();
    }
}