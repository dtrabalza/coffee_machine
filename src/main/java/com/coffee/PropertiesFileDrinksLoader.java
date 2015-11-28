package com.coffee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFileDrinksLoader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesFileDrinksLoader.class);

    private final Properties properties = new Properties();

    public PropertiesFileDrinksLoader(String fileName) throws IOException {
        try (InputStream in = getClass().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new IOException("Could not find " + fileName);
            }

            properties.load(in);
            logger.debug("Property file {} loaded", fileName);
        }
    }

    public PropertiesFileDrinksLoader(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            if (in == null) {
                throw new IOException("Could not find " + file);
            }

            properties.load(in);
            logger.debug("Property file {} loaded", file);
        }
    }

    public List<Drink> loadDrinks() {
        try {
            List<Drink> drinks = new ArrayList<>();

            int drinksNumber = Integer.parseInt((String) properties.getProperty("drinks.number", "0"));

            for (int i = 1; i <= drinksNumber; i++) {
                Drink drink = new Drink();
                drink.setName(properties.getProperty("drink" + i + ".name"));
                int ingredientNum = Integer.parseInt(properties.getProperty("drink" + i + ".ingredients"));
                if (ingredientNum <= 0) {
                    throw new IllegalStateException("Error in the property file");
                }
                for (int j = 1; j <= ingredientNum; j++) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(properties.getProperty("drink" + i + ".ingredient" + j + ".name"));
                    ingredient.setQuantity(Integer.parseInt(properties.getProperty("drink" + i + ".ingredient" + j + ".quantity")));
                    drink.addIngredient(ingredient);
                }
                drinks.add(drink);
            }

            return drinks;

        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            String message = "Drinks configuration file corrupted";
            logger.error(message);
            throw new RuntimeException(message);
        }
    }
}
