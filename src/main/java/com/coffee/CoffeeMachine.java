package com.coffee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeMachine {

    private static Logger logger = LoggerFactory.getLogger(CoffeeMachine.class);

    private Map<String, Ingredient> ingredientMap;

    private Map<String, Drink> drinksMap;

    public CoffeeMachine() {
        ingredientMap = new HashMap<>();
        drinksMap = new HashMap<>();
    }

    public CoffeeMachine(List<Ingredient> ingredients) {
        this();
        if (!Objects.nonNull(ingredients)) {
            throw new IllegalArgumentException("CoffeeMachine requires an ingredient list");
        }
        bulkAddIngredients(ingredients);
    }

    public Collection<Ingredient> getIngredients() {
        return this.ingredientMap.values();
    }

    public Map<String, Ingredient> getIngredientsMap() {
        return ingredientMap;
    }

    public Collection<Drink> getDrinks() {
        return this.drinksMap.values();
    }

    private void bulkAddIngredients(List<Ingredient> ingredients) {
        logger.info("Bulk load ingredients...");
        ingredients.stream().forEach((ingredient) -> {
            addIngredient(ingredient);
        });
        logger.info("Bulk load ingredients done");
    }

    public void addIngredient(Ingredient ingredient) throws IllegalArgumentException {
        if (!Objects.nonNull(ingredient)) {
            throw new IllegalArgumentException("CoffeeMachine requires a not null ingredient");
        }

        if (ingredientMap.containsKey(ingredient.getName())) {
            throw new IllegalArgumentException("You are trying to add twice the ingredient: "
                    + ingredient.getName());
        }
        logger.info("Adding {}", ingredient);
        this.ingredientMap.put(ingredient.getName(), ingredient);
    }

    protected void loadIngredientsFromMemory() {
        logger.info("Loading ingredients...");
        addIngredient(new Ingredient("Water", 20));
        addIngredient(new Ingredient("Coffee", 20));
        addIngredient(new Ingredient("Milk", 10));
        addIngredient(new Ingredient("Chocolate", 5));
        logger.info("Ingredients loaded");
    }

    public void addDrink(Drink drink) {
        if (!Objects.nonNull(drink)) {
            throw new IllegalArgumentException("CoffeeMachine requires a not null drink");
        }

        if (drinksMap.containsKey(drink.getName())) {
            throw new IllegalArgumentException("You are trying to add twice the drink: "
                    + drink.getName());
        }
        logger.info("Adding {}", drink);
        drinksMap.put(drink.getName(), drink);
    }

    protected void loadDrinksFromMemory() {
        logger.info("Loading drinks...");
        addDrink(new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1)));
        addDrink(new Drink("Coffee")
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Coffee", 1)));
        addDrink(new Drink("Cappuccino")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1))
                .addIngredient(new Ingredient("Milk", 1)));
        addDrink(new Drink("Chocolate")
                .addIngredient(new Ingredient("Milk", 2))
                .addIngredient(new Ingredient("Chocolate", 1)));
        logger.info("Drinks loaded");
    }

    public void init() {
        loadIngredientsFromMemory();
        loadDrinksFromMemory();
    }

    public List<String> getDrinksNameList() {
        List<String> drinkNames = new ArrayList<>();
        drinksMap.values().stream().forEach((drink) -> {
            drinkNames.add(drink.getName());
        });
        return drinkNames;
    }

    public Drink getDrinkByName(String drinkName) {
        return drinksMap.get(drinkName);
    }

    void prepareDrink(Drink drink) {
        logger.info("CoffeeMachine preparing: " + drink.getName());
        drink.getIngredients().stream().forEach((ingredient) -> {
            consumeIngredient(ingredient);
        });
    }

    private void consumeIngredient(Ingredient ingredient) {
        ingredientMap.get(ingredient.getName()).reduceQuantity(ingredient.getQuantity());
    }

    void loadDrinks(List<Drink> drinks) {
        drinks.stream().forEach((drink) -> {
            addDrink(drink);
        });
    }
}
