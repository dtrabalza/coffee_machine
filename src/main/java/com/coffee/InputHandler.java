package com.coffee;

import java.io.IOException;
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(InputHandler.class);

    public final static String LIST_COMMAND = "list";
    public final static String HELP_COMMAND = "help";

    public enum Command {

        LIST(LIST_COMMAND, "Lists the available drinks"),
        HELP(HELP_COMMAND, "Prints the help page");

        private final String name;
        private final String description;

        private Command(final String name, final String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

    }
    private final ConsoleReader console;

    public InputHandler() throws IOException {
        TerminalFactory.configure(TerminalFactory.Type.UNIX);
        console = new ConsoleReader();
        console.setPrompt("\u001B[32mcoffeeMachine\u001B[0m> ");
    }

    public void handleInput() throws IOException {

        printUsage();

        logger.info("Coffee machine ready");

        String line = null;
        while ((line = console.readLine()) != null) {
            switch (line.toLowerCase()) {
                case LIST_COMMAND:
                    logger.debug("User enter command \"{}\"", LIST_COMMAND);
                    console.println("Listing drinks...");
                    break;
                case HELP_COMMAND:
                    logger.debug("User enter command \"{}\"", HELP_COMMAND);
                    printUsage();
                    break;

                default:
                    logger.debug("User enter and unrecognised command");
                    printUsage();
            }
        }

    }

    public ConsoleReader getConsole() {
        return console;
    }

    public void printUsage() throws IOException {
        console.println();
        console.println("*************** HELP ***************");
        for (Command command : Command.values()) {
            console.println(command.getName() + "     " + command.getDescription());
        }
        console.println();
    }

}
