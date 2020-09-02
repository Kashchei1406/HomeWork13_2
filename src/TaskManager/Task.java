package TaskManager;

import TaskManager.service.PriorityTask;

import java.io.Serializable;
import java.time.*;


public class Task implements Serializable {

    long nowTime1 = Instant.now().getEpochSecond();
    private static final long serializableVersion = 1;

    private String name;
    private String category;
    private PriorityTask priorityTask;
    private LocalDate dateOfCompletion;

    public Task(String name, String category, PriorityTask priorityTask, LocalDate dateOfCompletion) {
        this.name = name;
        this.category = category;
        this.priorityTask = priorityTask;
        this.dateOfCompletion = dateOfCompletion;

    }

    ///region getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PriorityTask getPriority() {
        return priorityTask;
    }

    public void setPriority(PriorityTask priority) {
        this.priorityTask = priority;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }
    ///endregion

    @Override
    public String toString() {
        return "Task " +
                "name: '" + name + '\'' +
                ", category: '" + category + '\'' +
                ", priority :'" + priorityTask + '\'' +
                ", dateOfCompletion: " + dateOfCompletion;

    }


}
