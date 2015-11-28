package com.coffee;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class CoffeeMachineTest {

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineRaisesAnExceptionIfIngredientListIsNull() {
        new CoffeeMachine(null);
    }

    @Test
    public void coffeeMachineCanLoadOneIngredient() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == ingredients.size());
    }

    @Test
    public void coffeeMachineCanLoadIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));
        ingredients.add(new Ingredient("CoffeeBeans", 20));
        ingredients.add(new Ingredient("Milk", 10));
        ingredients.add(new Ingredient("Chocolate", 5));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == ingredients.size());
    }

    @Test
    public void coffeeMachineCanLoadIngredientsAfterBeingConstructed() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.addIngredient(new Ingredient("Water", 20));
        machine.addIngredient(new Ingredient("CoffeeBeans", 20));
        machine.addIngredient(new Ingredient("Milk", 10));
        machine.addIngredient(new Ingredient("Chocolate", 5));

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadTwiceTheSameIngredient() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));
        ingredients.add(new Ingredient("Water", 1));

        new CoffeeMachine(ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadANullIngredient() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addIngredient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadTwiceTheSameIngredient2() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        machine.addIngredient(new Ingredient("Water", 1));
    }

    @Test
    public void coffeeMachineLoadsIngredientsFromMemory() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.loadIngredientsFromMemory();

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == 4);
        assertTrue(machine.getIngredients().contains(new Ingredient("Water", 20)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Coffee", 20)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Milk", 10)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Chocolate", 5)));
    }

    @Test
    public void coffeeMachineCanAddDrinks() {
        Ingredient water = new Ingredient("Water", 1);
        Ingredient coffee = new Ingredient("Coffee", 1);

        Drink espresso = new Drink("Espresso");
        espresso.addIngredient(water);
        espresso.addIngredient(coffee);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addDrink(espresso);

        assertTrue(coffeeMachine.getDrinks().contains(espresso));
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotAddNullDrinks() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addDrink(null);

    }

}
