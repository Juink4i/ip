public class Task {
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

    public void UndoTask() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDesc();
    }
}