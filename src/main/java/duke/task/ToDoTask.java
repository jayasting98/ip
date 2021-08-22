package duke.task;

/**
 * Represents a to-do task.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ToDoTask extends Task {

    private static final String TYPE_MARK = "T";

    /**
     * Creates a to-do task with the given task description.
     *
     * @param taskDescription The description of the to-do task.
     */
    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getFileRepresentation() {
        int done = isDone() ? 1 : 0;
        String description = getDescription();
        return String.format("%s %d %s", TYPE_MARK, done, description);
    }

    @Override
    String getTypeIndicator() {
        return String.format("[%s]", TYPE_MARK);
    }
}
