package com.coffee;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String DRINKS_FILE = "drinks.properties";

    public static void main(String[] args) throws IOException {

        InputHandler inputHandler = new InputHandler();

//        CoffeeMachine coffeeMachine = new CoffeeMachine();
        CoffeeMachine coffeeMachine = new CoffeeMachine(DRINKS_FILE);
        inputHandler.print("Loading coffee machine...");
        coffeeMachine.init();
        inputHandler.print("Coffee machine loaded and ready!");

        inputHandler.setCoffeeMachine(coffeeMachine);

        logger.info("Coffee machine started");
        System.out.println("Super cool coffee machine started!");

        inputHandler.handleInput();

    }

}
