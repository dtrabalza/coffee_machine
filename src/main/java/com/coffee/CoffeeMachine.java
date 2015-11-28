package com.coffee;

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
        logger.info("Adding ingredient {}", ingredient);
        this.ingredientMap.put(ingredient.getName(), ingredient);
    }

    public void loadIngredientsFromMemory() {
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
        logger.info("Adding drink: {}", drink);
        drinksMap.put(drink.getName(), drink);
    }
}
