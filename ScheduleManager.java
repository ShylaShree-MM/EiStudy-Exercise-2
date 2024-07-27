import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    private ScheduleManager() {

    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Task conflictingTask) {
        for (TaskObserver observer : observers) {
            observer.notifyConflict(conflictingTask);
        }
    }

    public String addTask(Task task) {
        for (Task existingTask : tasks) {
            if (task.overlapsWith(existingTask)) {
                notifyObservers(existingTask);
                return "Error: Task conflicts with existing task \"" + existingTask.getDescription() + "\".";
            }
        }
        tasks.add(task);
        return "Task added successfully. No conflicts.";
    }

    public String removeTask(String description) {
        Task toRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                toRemove = task;
                break;
            }
        }
        if (toRemove != null) {
            tasks.remove(toRemove);
            return "Task removed successfully.";
        }
        return "Error: Task not found.";
    }

    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks scheduled for the day.";
        }
        Collections.sort(tasks, (t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }
}
