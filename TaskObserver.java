public interface TaskObserver {
    void notifyConflict(Task conflictingTask);
}
