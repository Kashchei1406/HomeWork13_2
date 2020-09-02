package TaskManager;

import java.util.Comparator;

public class LocalDateComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return (int) (o1.getDateOfCompletion().toEpochDay() - o2.getDateOfCompletion().toEpochDay());
    }
}
