package com.coffee;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Coffee machine started");
        System.out.println("Super cool coffee machine started!");

        InputHandler inputHandler = new InputHandler();

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        inputHandler.print("Loading coffee machine...");
        coffeeMachine.init();
        inputHandler.print("Coffee machine loaded and ready!");
        
        inputHandler.setCoffeeMachine(coffeeMachine);
        
        try {
            inputHandler.handleInput();
        } catch (IOException | RuntimeException e) {
            logger.error("Error occurred", e);
            System.exit(0);
        }

    }

}
