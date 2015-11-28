package com.coffee;

import org.junit.Test;
import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void canCreateDrinksInConstructor() {
        Ingredient water = new Ingredient("Water", 1);
        Ingredient coffee = new Ingredient("Coffee", 1);

        Drink espresso = new Drink("Espresso");
        espresso.addIngredient(water);
        espresso.addIngredient(coffee);

        assertTrue("Espresso".equals(espresso.getName()));
        assertTrue(espresso.getIngredients().contains(water));
        assertTrue(espresso.getIngredients().contains(coffee));
        assertTrue(espresso.getIngredients().size() == 2);
    }

    @Test
    public void canCreateDrinks() {
        Ingredient water = new Ingredient("Water", 1);
        Ingredient coffee = new Ingredient("Coffee", 1);

        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(water);
        espresso.addIngredient(coffee);

        assertTrue("Espresso".equals(espresso.getName()));
        assertTrue(espresso.getIngredients().contains(water));
        assertTrue(espresso.getIngredients().contains(coffee));
        assertTrue(espresso.getIngredients().size() == 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotAddTwiceTheSameIngredientToADrink() {
        Ingredient water1 = new Ingredient("Water", 1);
        Ingredient water2 = new Ingredient("Water", 2);

        Drink espresso = new Drink("Espresso");
        espresso.addIngredient(water1);
        espresso.addIngredient(water2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateDrinksWithInvalidName() {
        new Drink("124");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateDrinksWithNullName() {
        new Drink(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateDrinksWithEmptyName() {
        new Drink("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateDrinksWithOnlyAWhiteSpaceAsName() {
        new Drink(" ");
    }

    @Test
    public void canCreateDrinksWithFiveIngredients() {
        Drink drink = new Drink("ImpossibleDrink");
        drink.addIngredient(new Ingredient("One", 1));
        drink.addIngredient(new Ingredient("Two", 2));
        drink.addIngredient(new Ingredient("Three", 3));
        drink.addIngredient(new Ingredient("Four", 4));
        drink.addIngredient(new Ingredient("Five", 5));

        assertTrue(drink.getIngredients().size() == 5);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotCreateDrinksWithMoreThanFiveIngredients() {
        Drink drink = new Drink("ImpossibleDrink");
        drink.addIngredient(new Ingredient("One", 1));
        drink.addIngredient(new Ingredient("Two", 2));
        drink.addIngredient(new Ingredient("Three", 3));
        drink.addIngredient(new Ingredient("Four", 4));
        drink.addIngredient(new Ingredient("Five", 5));
        drink.addIngredient(new Ingredient("Six", 6));
        drink.addIngredient(new Ingredient("Seven", 7));
    }

}
