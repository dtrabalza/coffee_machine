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

    public CoffeeMachine() {
        ingredientMap = new HashMap<>();
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

    private void bulkAddIngredients(List<Ingredient> ingredients) {
        logger.info("Bulk load ingredients...");
        ingredients.stream().forEach((ingredient) -> {
            addIngredient(ingredient);
        });
        logger.info("Bulk load ingredients done");
    }

    public void addIngredient(Ingredient ingredient) throws IllegalArgumentException {
        if (ingredientMap.containsKey(ingredient.getName())) {
            throw new IllegalArgumentException("You are trying to load twice the ingredient: "
                    + ingredient.getName());
        } else {
            logger.info("Adding ingredient {}", ingredient);
            this.ingredientMap.put(ingredient.getName(), ingredient);
        }
    }

}
