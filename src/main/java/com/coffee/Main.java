package com.coffee;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Coffee machine started");
        System.out.println("Super cool coffee machine ready to use!");

        InputHandler inputHandler = new InputHandler();

        try {
            inputHandler.handleInput();
        } catch (IOException | RuntimeException e) {
            logger.error("Error occurred", e);
            inputHandler.printUsage();
        }

    }

}
