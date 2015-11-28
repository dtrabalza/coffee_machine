package com.coffee;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputHandler {

    private static final Logger logger = LoggerFactory.getLogger(InputHandler.class);

    private CoffeeMachine coffeeMachine;

    public final static String LIST_COMMAND = "list";
    public final static String HELP_COMMAND = "help";
    public final static String PREPARE_COMMAND = "prepare";

    public enum Command {

        LIST(LIST_COMMAND, "Lists the available drinks"),
        HELP(HELP_COMMAND, "Prints the help page"),
        PREPARE(PREPARE_COMMAND, "prepare {drink_name} prepares the selected drink");

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

    public InputHandler() {
        try {
            TerminalFactory.configure(TerminalFactory.Type.UNIX);
            console = new ConsoleReader();
            console.setPrompt("\u001B[32mcoffeeMachine\u001B[0m> ");
        } catch (IOException ex) {
            logger.error("Could not initialize InputHandler", ex);
            throw new RuntimeException("Could not initialize InputHandler");
        }
    }

    public void handleInput() throws IOException {

        printUsage();

        logger.info("Coffee machine ready");

        String line = null;
        while ((line = console.readLine()) != null) {
            if (line.trim().equalsIgnoreCase(LIST_COMMAND)) {
                logger.debug("User enter command \"{}\"", LIST_COMMAND);
                print("Available drinks are:");
                print(coffeeMachine.getDrinks());
            } else if (line.trim().equalsIgnoreCase(HELP_COMMAND)) {
                logger.debug("User enter command \"{}\"", HELP_COMMAND);
                printUsage();
            } else if (line.startsWith(PREPARE_COMMAND)) {
                String drinkName = line.substring(line.indexOf(PREPARE_COMMAND) + PREPARE_COMMAND.length()).trim();
                if (coffeeMachine.getDrinksNameList().contains(drinkName)) {
                    Drink drink = coffeeMachine.getDrinkByName(drinkName);
                    try {
                        coffeeMachine.prepareDrink(drink);
                        print("Your drink is ready! Enjoy your " + drink);
                        printCoffeeMachineIngredients();
                    }catch(IllegalStateException e){
                        print("Cannot prepare your drink (probably there are not enough ingredients!). "
                                + "Please ask the coffee machine guy or whichever italian you can find");
                    }
                } else {
                    print("Sorry, drink not present. Try listing the drinks using the command \"" + LIST_COMMAND + "\"");
                }
            } else {
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
        printCoffeeMachineIngredients();
        console.println("*************** HELP ***************");
        console.println("CTRL+D" + "   " + "Quit");
        for (Command command : Command.values()) {
            console.println(command.getName() + "     " + command.getDescription());
        }
        console.println("*************** HELP ***************");
        console.println();
    }

    private void printCoffeeMachineIngredients() throws IOException {
        console.println();
        console.println("********** COFFEE MACHINE INGREDIENTS **********");
        for (Ingredient ingredient : coffeeMachine.getIngredients()) {
            console.println(ingredient.getName() + ": " + ingredient.getQuantity());
        }
        console.println("********** COFFEE MACHINE INGREDIENTS **********");
        console.println();
    }

    public void print(String s) {
        try {
            console.println(s);
        } catch (IOException ex) {
            logger.error("Could not print to console", ex);
        }
    }

    public void print(Collection collection) {
        collection.stream().forEach((c) -> {
            print(c.toString());
        });
    }

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;

        List<Completer> completors = new LinkedList<>();

        completors.add(new AggregateCompleter(
                new ArgumentCompleter(new StringsCompleter(PREPARE_COMMAND), new StringsCompleter(coffeeMachine.getDrinksNameList()), new NullCompleter()),
                new ArgumentCompleter(new StringsCompleter(LIST_COMMAND), new NullCompleter()),
                new ArgumentCompleter(new StringsCompleter(HELP_COMMAND), new NullCompleter())
        )
        );

        completors.stream().forEach((c) -> {
            console.addCompleter(c);
        });
    }

}
