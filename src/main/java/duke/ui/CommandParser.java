package duke.ui;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

import java.util.StringTokenizer;

/**
 * Helper class to parse commands.
 *
 * @author Jay Aljelo Saez Ting
 */
public class CommandParser {
    private static final String EXIT_COMMAND = "bye";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String LIST_TASKS_COMMAND = "list";
    private static final String MARK_TASK_DONE_COMMAND = "done";
    private static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    private static final String ADD_EVENT_TASK_COMMAND = "event";

    /**
     * Parses the commands to get the command name.
     *
     * @param command The command.
     * @return The type of command.
     * @throws IllegalArgumentException If the command is empty, or the command does not exist.
     */
    public CommandType getCommandTypeFromCommand(String command) throws IllegalArgumentException {
        StringTokenizer st = new StringTokenizer(command);
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("This command is empty.");
        }
        String commandName = st.nextToken();
        switch (commandName) {
        case EXIT_COMMAND:
            return CommandType.EXIT;
        case ADD_TODO_COMMAND:
            return CommandType.ADD_TODO_TASK;
        case LIST_TASKS_COMMAND:
            return CommandType.LIST_TASKS;
        case MARK_TASK_DONE_COMMAND:
            return CommandType.MARK_TASK_DONE;
        case ADD_DEADLINE_TASK_COMMAND:
            return CommandType.ADD_DEADLINE_TASK;
        case ADD_EVENT_TASK_COMMAND:
            return CommandType.ADD_EVENT_TASK;
        default:
            throw new IllegalArgumentException("This command does not exist.");
        }
    }

    /**
     * Parses a "Mark Task Done" command to get the index of the task to be marked done in the list of tasks.
     *
     * @param command The "Mark Task Done" command.
     * @return The index of the task.
     * @throws IllegalArgumentException If the command is empty, not a "Mark Task Done" command, or malformed.
     */
    public int getTaskIndexOfTaskMarkedDone(String command) throws IllegalArgumentException {
        StringTokenizer st = new StringTokenizer(command);
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("This command is empty.");
        }
        String commandName = st.nextToken();
        if (!commandName.equals(MARK_TASK_DONE_COMMAND)) {
            throw new IllegalArgumentException("This command is not a 'Mark Task Done' command.");
        }
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("A task number needs to be specified for a 'Mark Task Done' command.");
        }
        try {
            return Integer.parseInt(st.nextToken()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The task number is not a number.");
        }
    }

    /**
     * Parses an "Add To-do Task" command to get the to-do task.
     *
     * @param command The "Add To-do Task" command.
     * @return The to-do task.
     * @throws IllegalArgumentException If the command is empty, not an 'Add To-do Task' command, or malformed.
     */
    public ToDoTask getToDoTask(String command) throws IllegalArgumentException {
        StringTokenizer st = new StringTokenizer(command);
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("This command is empty.");
        }
        String commandName = st.nextToken();
        if (!commandName.equals(ADD_TODO_COMMAND)) {
            throw new IllegalArgumentException("This command is not an 'Add To-do Task' command.");
        }
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("A description needs to be specified for an 'Add To-do Task' command.");
        }
        String taskDescription = st.nextToken("").strip();
        return new ToDoTask(taskDescription);
    }

    /**
     * Parses an "Add Deadline Task" command to get the deadline task.
     *
     * @param command The "Add Deadline Task" command.
     * @return The deadline task.
     * @throws IllegalArgumentException If the command is empty, not an "Add Deadline Task" command, or malformed.
     */
    public DeadlineTask getDeadlineTask(String command) throws IllegalArgumentException {
        String[] tokens = command.split(" ");
        if (tokens.length == 0) {
            throw new IllegalArgumentException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(ADD_DEADLINE_TASK_COMMAND)) {
            throw new IllegalArgumentException("This command is not an 'Add Deadline Task' command.");
        }
        if (tokens.length == 1) {
            throw new IllegalArgumentException(
                    "A task description needs to be specified for an 'Add Deadline Task' command.");
        }
        StringBuilder taskDescription = new StringBuilder();
        int timeStartIndex = -1;
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("/by")) {
                timeStartIndex = i + 1;
                break;
            }
            taskDescription.append(token).append(" ");
        }
        if (timeStartIndex == -1 || timeStartIndex == tokens.length) {
            throw new IllegalArgumentException("A deadline needs to be specified for an 'Add Deadline Task' command.");
        }
        StringBuilder deadline = new StringBuilder();
        for (int i = timeStartIndex; i < tokens.length; i++) {
            deadline.append(tokens[i]).append(" ");
        }
        return new DeadlineTask(taskDescription.toString().strip(), deadline.toString().strip());
    }

    /**
     * Parses an "Add Event Task" command to get the event task.
     *
     * @param command The "Add Event Task" command.
     * @return The event task.
     * @throws IllegalArgumentException If the command is empty, not an "Add Event Task" command, or malformed.
     */
    public EventTask getEventTask(String command) throws IllegalArgumentException {
        String[] tokens = command.split(" ");
        if (tokens.length == 0) {
            throw new IllegalArgumentException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(ADD_EVENT_TASK_COMMAND)) {
            throw new IllegalArgumentException("This command is not an 'Add Event Task' command.");
        }
        if (tokens.length == 1) {
            throw new IllegalArgumentException(
                    "A task description needs to be specified for an 'Add Event Task' command.");
        }
        StringBuilder taskDescription = new StringBuilder();
        int timeStartIndex = -1;
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("/at")) {
                timeStartIndex = i + 1;
                break;
            }
            taskDescription.append(token).append(" ");
        }
        if (timeStartIndex == -1 || timeStartIndex == tokens.length) {
            throw new IllegalArgumentException("A deadline needs to be specified for an 'Add Event Task' command.");
        }
        StringBuilder deadline = new StringBuilder();
        for (int i = timeStartIndex; i < tokens.length; i++) {
            deadline.append(tokens[i]).append(" ");
        }
        return new EventTask(taskDescription.toString().strip(), deadline.toString().strip());
    }
}