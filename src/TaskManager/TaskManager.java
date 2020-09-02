package TaskManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class TaskManager implements Serializable {

    private static final long serializableVersion = 1;


    private List<Task> taskList = new ArrayList<>();
    private int howMuchTask = 0;

    public void addTask(Task task) {
        taskList.add(task);
        howMuchTask++;
    }



    public void deleteTask(String task) {
        Task taskToRemove = null;

        for (Task taskInList : taskList){
            if(taskInList.getName().equals(task)){
                taskToRemove = taskInList;
            }
        }

        if (taskToRemove != null) {
            taskList.remove(taskToRemove);
            howMuchTask--;

        }
    }


    ///region getters/setters

    public List<Task> getTaskList() {
        return taskList;
    }

    public TaskManager setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        return this;
    }

    public int getHowMuchTask() {
        return howMuchTask;
    }

    public TaskManager setHowMuchTask(int howMuchTask) {
        this.howMuchTask = howMuchTask;
        return this;
    }
    ///endregion


    @Override
    public String toString() {
        return "TaskManager{" +
                "taskList=" + taskList +
                ", howMuchTask=" + howMuchTask +
                '}';
    }


}
