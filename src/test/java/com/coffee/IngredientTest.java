package com.coffee;

import static org.junit.Assert.*;

import org.junit.Test;

public class IngredientTest {

    @Test
    public void canCreateIngredientsInConstructor() {
        Ingredient water = new Ingredient("Water", 10);

        assertEquals("Water", water.getName());
        assertTrue(10 == water.getQuantity());
    }

    @Test
    public void canCreateIngredientsWithSetters() {
        Ingredient water = new Ingredient();
        water.setName("Water");
        water.setQuantity(10);

        assertEquals("Water", water.getName());
        assertTrue(10 == water.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateIngredientsWithEmptyName() {
        new Ingredient("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateIngredientsWithNullName() {
        new Ingredient(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateIngredientsWithZeroOrLowerQuantity() {
        new Ingredient("Water", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateIngredientsWithQuantityMoreThan100() {
        new Ingredient("Water", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateIngredientsWithNameLongerThan30Characters() {
        new Ingredient("abcdefghijabcdefghijabcdefghijabcdefghij", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createIngredientsRaisesAnExceptionForInvalidIngredientName() {
        new Ingredient("12345", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createIngredientsRaisesAnExceptionForStrangeIngredientName() {
        new Ingredient("\"", 10);
    }

    @Test
    public void decreaseQuantityDecreasesIngredientsQuantity() {
        Ingredient water = new Ingredient("Water", 10);

        water.reduceQuantity(2);

        assertTrue(8 == water.getQuantity());
    }

    @Test(expected = IllegalStateException.class)
    public void decreaseQuantityDoesNotRemoveMoreThanTheTotalIngredient() {
        Ingredient water = new Ingredient("Water", 2);

        water.reduceQuantity(10);
    }

}
