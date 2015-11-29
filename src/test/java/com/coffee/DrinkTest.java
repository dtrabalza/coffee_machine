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

    @Test
    public void increaseStrengthIncreasesTheFirstIngredientByOne() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.increaseStrength(1);

        assertTrue(espresso.getIngredientByName("Coffee").getQuantity() == 3);
        assertTrue(espresso.getIngredientByName("Water").getQuantity() == 2);
    }

    @Test(expected = IllegalStateException.class)
    public void getIngredientByNameRaisesExceptionIfIngredientNotExisting() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.getIngredientByName("Milk");
    }

    @Test
    public void decreaseStrengthDecreasesTheFirstIngredientByOne() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.decreaseStrength(1);

        assertTrue(espresso.getIngredientByName("Coffee").getQuantity() == 1);
        assertTrue(espresso.getIngredientByName("Water").getQuantity() == 2);
    }

    @Test
    public void CopyConstructorWorks() {
        Drink d1 = new Drink("Espresso");
        d1.addIngredient(new Ingredient("Coffee", 2));
        d1.addIngredient(new Ingredient("Water", 2));

        assertEquals(new Ingredient("Coffee", 2),
                d1.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2),
                d1.getIngredientByName("Water"));

        Drink d2 = new Drink(d1);

        assertEquals(new Ingredient("Coffee", 2),
                d2.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2),
                d2.getIngredientByName("Water"));

        d2.decreaseStrength(1);

        assertEquals(new Ingredient("Coffee", 1),
                d2.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2),
                d2.getIngredientByName("Water"));

        assertEquals(new Ingredient("Coffee", 2),
                d1.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2),
                d1.getIngredientByName("Water"));
    }

    @Test
    public void canAddMilkIfDrinkDoesNotContainAlreadyMilk() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        assertTrue(espresso.canAddMilk());
    }

    @Test
    public void cannotAddMilkIfDrinkHasMilk() {
        Drink cappuccino = new Drink();
        cappuccino.setName("Cappuccino");
        cappuccino.addIngredient(new Ingredient("Coffee", 2));
        cappuccino.addIngredient(new Ingredient("Water", 2));
        cappuccino.addIngredient(new Ingredient("Milk", 2));

        assertFalse(cappuccino.canAddMilk());
    }

    @Test
    public void canAddSugarToADrink() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.addSugar();

        assertEquals(new Ingredient("Sugar", 1), espresso.getIngredientByName("Sugar"));
        assertEquals(new Ingredient("Coffee", 2), espresso.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2), espresso.getIngredientByName("Water"));
    }

    @Test
    public void canAddMiltipleSugarToADrink() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.addSugar();
        espresso.addSugar();
        espresso.addSugar();

        assertEquals(new Ingredient("Sugar", 3), espresso.getIngredientByName("Sugar"));
        assertEquals(new Ingredient("Coffee", 2), espresso.getIngredientByName("Coffee"));
        assertEquals(new Ingredient("Water", 2), espresso.getIngredientByName("Water"));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAddTooMuchSugarToADrink() {
        Drink espresso = new Drink();
        espresso.setName("Espresso");
        espresso.addIngredient(new Ingredient("Coffee", 2));
        espresso.addIngredient(new Ingredient("Water", 2));

        espresso.addSugar();
        espresso.addSugar();
        espresso.addSugar();
        espresso.addSugar();
    }
}
