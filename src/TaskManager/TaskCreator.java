package TaskManager;

import TaskManager.service.PriorityTask;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskCreator implements Serializable {

    private static final long serializableVersion = 1;

    private static final Scanner scanner = new Scanner(System.in);
    private static String choice;
    private static boolean marker = true;


    public Task create() {
        return new Task(createName(), createCategory(), createPriority(), createDate());
    }

    private static String createName() {
        System.out.println("Inter Name");
        return scanner.nextLine();
    }

    private static String createCategory() {
        System.out.println("Category Task:");
        return scanner.nextLine();
    }

    private static PriorityTask createPriority() {
        System.out.println("Important task? yes/no ");
        choice = scanner.nextLine();

        return switch (choice) {
            case "yes" -> PriorityTask.IMPORTANT;
            case "no" -> PriorityTask.NOT_IMPORTANT;
            default -> null;
        };


    }

    private static LocalDate createDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = null;
        String dateInStr;


        while (marker) {
            marker = false;
            System.out.println("Inter day of completion: dd/MM/yyyy ");
            dateInStr = scanner.nextLine();
            try {
                localDate = LocalDate.parse(dateInStr, dateTimeFormatter);
            } catch (DateTimeException e) {
                System.out.println("Date format incorrect");
                marker = true;
            }
        }

        return localDate;
    }

}
