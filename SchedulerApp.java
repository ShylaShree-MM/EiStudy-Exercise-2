import java.util.Scanner;

public class SchedulerApp {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();

        manager.addObserver(conflictingTask -> System.out.println("Conflict detected with task: " + conflictingTask));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Task\n2. Remove Task\n3. View Tasks\n4. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter task description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter start time (HH:MM):");
                    String startTime = scanner.nextLine();
                    System.out.println("Enter end time (HH:MM):");
                    String endTime = scanner.nextLine();
                    System.out.println("Enter priority level:");
                    String priority = scanner.nextLine();
                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    System.out.println(manager.addTask(task));
                    break;
                case "2":
                    System.out.println("Enter task description to remove:");
                    String removeDescription = scanner.nextLine();
                    System.out.println(manager.removeTask(removeDescription));
                    break;
                case "3":
                    System.out.println(manager.viewTasks());
                    break;
                case "4":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
