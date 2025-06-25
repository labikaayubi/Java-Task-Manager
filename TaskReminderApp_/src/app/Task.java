package app;

public class Task {
    private String title;
    private String dueDate;  
    private boolean isCompleted;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "✓ Completed" : "⏳ Pending";
        return title + " | Due: " + dueDate + " | " + status;
    }
}

