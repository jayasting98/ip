package duke.command;

import duke.task.Task;
import duke.task.TaskHandler;
import duke.ui.Ui;

import java.util.List;
import java.util.Map;

/**
 * Represents a "Find Tasks" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class FindTasksCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.FIND_TASKS;

    private String queryTaskDescription;

    /**
     * Creates a FindTasksCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public FindTasksCommand(String command) throws DukeInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws DukeInvalidCommandException {
        List<Map.Entry<Integer, Task>> queryResults = taskHandler.findTasksDescribedBy(queryTaskDescription);
        if (queryResults.size() == 0) {
            ui.startMessage()
                    .addLine("No matching tasks were found.")
                    .printFormatted();
        } else {
            ui.startMessage()
                    .addLine("Here are the matching tasks in your list:")
                    .addFindTasksResultsList(queryResults)
                    .printFormatted();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws DukeInvalidCommandException {
        StringBuilder queryTaskDescriptionSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            queryTaskDescriptionSb.append(tokens[i]).append(" ");
        }
        String queryTaskDescription = queryTaskDescriptionSb.toString().strip();
        if (queryTaskDescription.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A query is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
        this.queryTaskDescription = queryTaskDescription;
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}