package TaskManager.service.impl;

import TaskManager.TaskManager;
import TaskManager.service.TaskManagerStoring;

import java.io.*;

public class TaskManagerStoringImpl implements TaskManagerStoring {

    private static final String FILENAME = "TaskManager.txt";


    @Override
    public void saveTaskManager(TaskManager taskManager) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskManager);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public TaskManager readTaskManager() {
        TaskManager taskManager = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(FILENAME);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            if (object instanceof TaskManager) {
                taskManager = (TaskManager) object;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception reading TaskManager");
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return taskManager;

    }
}
