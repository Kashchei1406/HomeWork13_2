package TaskManager;

import TaskManager.service.PriorityTask;
import TaskManager.service.TaskManagerStoring;
import TaskManager.service.impl.TaskManagerStoringImpl;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {


    private static TaskManager taskManager = new TaskManager();
    private static TaskManagerStoring taskManagerStoring = new TaskManagerStoringImpl();
    private static List<Task> taskList = new ArrayList<>();
    private static final TaskCreator taskCreator = new TaskCreator();
    public static long nowTimeEpochHours;
    private static final Comparator<Task> localDateComparator = new LocalDateComparator();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.now();
        String choice;
        boolean marker = true;


        load();

        System.out.println("Today: " + localDate);

        while (marker) {
            nowTimeEpochHours = ((Instant.now().getEpochSecond()) / 3600);
            printAndSortTasks(taskManager.getTaskList(), taskManager.getHowMuchTask());
            System.out.println("Your action add/delete/edit or stop: ");

            choice = scanner.nextLine();
            if (!choice.equals("stop")) {
                userActions(choice);

            } else marker = false;

        }

        taskManagerStoring.saveTaskManager(taskManager);


    }


    public static void load() {
        taskManager = taskManagerStoring.readTaskManager();
        taskList.addAll(taskManager.getTaskList());

    }

    private static void LastTimeToNextTask(List<Task> taskList) {

        String nameNextTask;
        int hour;
        int day;
        int index = 0;
        long localDateTask = (taskList.get(index).getDateOfCompletion().toEpochDay()) * 24;
        int lastTime = (int) (localDateTask - nowTimeEpochHours);

        while (nowTimeEpochHours > localDateTask) {

            index ++;
            localDateTask = taskList.get(index).getDateOfCompletion().toEpochDay() * 24;
            lastTime = (int) (localDateTask - nowTimeEpochHours);

        }
        day = lastTime / 24;
        hour = lastTime - (day * 24);

        if (day <= 0) {
            hour = lastTime;
        }
        nameNextTask = taskList.get(index).getName();

        System.out.printf("Last time To the next Task '%s':  %d day , %d hour. %n", nameNextTask, day, hour);
    }


    private static void printAndSortTasks(List<Task> taskList, Integer howMuchTask) {

        String taskOrTasks;

        taskList.sort(localDateComparator);
        LastTimeToNextTask(taskList);

        if (howMuchTask > 1) {
            taskOrTasks = "tasks";
        } else {
            taskOrTasks = "task";
        }

        System.out.printf("You have %d %s! %n", howMuchTask, taskOrTasks);
        taskList.forEach(System.out::println);


    }

    private static void userActions(String action) {

        Scanner scanner = new Scanner(System.in);
        String editChoice;

        switch (action) {
            case "add":
                taskManager.addTask(taskCreator.create());
                break;
            case "delete":
                System.out.println("Task name for delete: ");
                String nameTask = scanner.nextLine();
                taskManager.deleteTask(nameTask);
                break;
            case "edit":
                System.out.println("Edit: name/category/priority/date");
                editChoice = scanner.nextLine();
                actionTask(editChoice);
                break;
            default:
                System.out.println("Incorrect");

        }
    }

    private static void actionTask(String str) {

        Scanner scanner = new Scanner(System.in);

        String stringNew, action;
        Task task = null;

        switch (str) {
            case "name":
                System.out.println("Inter task name for edit ");
                action = scanner.nextLine();

                for (Task taskInList : taskManager.getTaskList()) {
                    if (taskInList.getName().equals(action)) {
                        task = taskInList;
                    }
                }

                System.out.println("Enter new name task");
                stringNew = scanner.nextLine();

                if (task != null) {
                    task.setName(stringNew);
                } else System.out.println("Enter incorrect");
                break;

            case "category":
                System.out.println("Inter task name for edit category ");
                action = scanner.nextLine();

                for (Task taskInList : taskManager.getTaskList()) {
                    if (taskInList.getName().equals(action)) {
                        task = taskInList;
                    }
                }

                System.out.println("Enter new category task");
                stringNew = scanner.nextLine();

                if (task != null) {
                    task.setCategory(stringNew);
                } else System.out.println("Enter incorrect");
                break;

            case "priority":
                System.out.println("Inter task name for edit priority");
                action = scanner.nextLine();

                for (Task taskInList : taskManager.getTaskList()) {
                    if (taskInList.getName().equals(action)) {
                        task = taskInList;
                    }
                }
                if (task != null) {

                    if (task.getPriority().equals(PriorityTask.IMPORTANT)) {
                        task.setPriority(PriorityTask.NOT_IMPORTANT);

                    } else {
                        task.setPriority(PriorityTask.IMPORTANT);
                    }
                } else System.out.println("Enter incorrect");
                break;

            case "date":
                System.out.println("Inter task name for edit ");
                action = scanner.nextLine();

                for (Task taskInList : taskManager.getTaskList()) {
                    if (taskInList.getName().equals(action)) {
                        task = taskInList;
                    }
                }
                if (task != null) {

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = null;
                    String dateInStr;
                    boolean marker = true;

                    while (marker) {
                        marker = false;
                        System.out.println("Inter new day of completion: dd/MM/yyyy ");
                        dateInStr = scanner.nextLine();
                        try {
                            localDate = LocalDate.parse(dateInStr, dateTimeFormatter);
                        } catch (DateTimeException e) {
                            System.out.println("Date format incorrect");
                            marker = true;
                        }
                    }

                    task.setDateOfCompletion(localDate);

                } else System.out.println("Enter incorrect");

                break;

        }

    }


}
