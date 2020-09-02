package TaskManager.service;

import TaskManager.TaskManager;

import java.awt.*;

public interface TaskManagerStoring {


    void saveTaskManager(TaskManager taskManager);

    TaskManager readTaskManager();

}
