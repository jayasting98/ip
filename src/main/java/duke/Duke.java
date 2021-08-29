package duke;

import javafx.application.Application;

/**
 * Main class of Duke
 *
 * @author Jay Aljelo Saez Ting
 */
public class Duke {
    /**
     * Initialises and runs the Duke chatbot.
     * Command line arguments are ignored.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(DukeChatbot.class, args);
    }
}
