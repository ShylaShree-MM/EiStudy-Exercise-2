import java.time.LocalTime;

public class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            return new Task(description, start, end, priority);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:MM.");
        }
    }
}
