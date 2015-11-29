package com.coffee.dao;

import com.coffee.core.Drink;
import com.coffee.core.Ingredient;
import com.coffee.dao.PropertiesFileDrinksLoader;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesFileDrinksLoaderTest {

    @Test
    public void propertyFilesLoadedSuccessfully() throws IOException {
        new PropertiesFileDrinksLoader("../../../drinks.properties");
    }

    @Test(expected = IOException.class)
    public void ioExceptionIfPropertyFilesNotLoaded() throws IOException {
        new PropertiesFileDrinksLoader("gaerhtjf");
    }

    @Test
    public void loadDrinksWorks() throws IOException {
        PropertiesFileDrinksLoader loader = new PropertiesFileDrinksLoader("../../../drinks.properties");

        List<Drink> drinks = loader.loadDrinks();

        assertNotNull(drinks);

        assertTrue(drinks.size() == 4);
        assertTrue(drinks.get(0).getName().equals("Espresso"));
        assertTrue(drinks.get(2).getIngredients().contains(new Ingredient("Coffee", 2)));
        assertTrue(drinks.get(3).getName().equals("Chocolate"));
    }

}
