package com.coffee.core;

import com.coffee.input.InputHandler;
import com.coffee.utils.Validator;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Drink {

    private static final Logger logger = LoggerFactory.getLogger(InputHandler.class);
    
    private final int MAX_NUMBER_OF_INGREDIENTS = 5;
    private final int MAX_SUGAR_QUANTITY = 3;

    private String name;

    private Map<String, Ingredient> ingredientsMap;

    public Drink() {
        ingredientsMap = new LinkedHashMap<>();
    }

    public Drink(Drink copy) {
        this.name = copy.getName();
        this.ingredientsMap = deepCopy(copy.getIngredientsMap());
    }

    public Drink(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredientsMap.values();
    }

    public Drink addIngredient(Ingredient ingredient) {
        if (ingredientsMap.containsKey(ingredient.getName())) {
            throw new IllegalArgumentException("Cannot add twice the same ingredient");
        }

        if (ingredientsMap.size() >= MAX_NUMBER_OF_INGREDIENTS) {
            throw new IllegalStateException("Cannot add more than five ingredients");
        }

        logger.info("Adding {} to {}", ingredient, this);
        
        ingredientsMap.put(ingredient.getName(), ingredient);
        return this;
    }

    @Override
    public String toString() {
        return "Drink: " + name + " " + ingredientsMap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.ingredientsMap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drink other = (Drink) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.ingredientsMap, other.ingredientsMap);
    }

    public Ingredient getIngredientByName(String ingredientName) {
        if (!ingredientsMap.containsKey(ingredientName)) {
            throw new IllegalStateException("Could not find ingredient " 
                    + ingredientName + " in " + this);
        }
        return ingredientsMap.get(ingredientName);
    }

    public void increaseStrength(int value) {
        ingredientsMap.entrySet().iterator().next().getValue().increaseQuantity(value);
    }

    public void decreaseStrength(int value) {
        ingredientsMap.entrySet().iterator().next().getValue().decreaseQuantity(value);
    }

    public Map<String, Ingredient> getIngredientsMap() {
        return ingredientsMap;
    }

    private Map<String, Ingredient> deepCopy(Map<String, Ingredient> source) {
        Map<String, Ingredient> clonedMap = new LinkedHashMap<>();
        Iterator<String> iterator = source.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            clonedMap.put(key, new Ingredient(source.get(key)));
        }
        
        return clonedMap;
    }

    public boolean canAddMilk() {
        return !ingredientsMap.containsKey("Milk");
    }

    public void addMilk() {
        addIngredient(new Ingredient("Milk", 1));
    }

    public void addSugar() {
        String ingredientName = "Sugar";
        if (ingredientsMap.containsKey(ingredientName)) {           
            Ingredient sugar = ingredientsMap.get(ingredientName);
            if (sugar.getQuantity() < MAX_SUGAR_QUANTITY) {
                logger.debug("{} already present in {}. Increasing its quantity", ingredientName, this);
                sugar.increaseQuantity(1);
            } else {
                throw new IllegalStateException("Cannot add more sugar; I will not held responsible for your cholesterol!");
            }
        } else {
            logger.debug("{} not present in {}. Adding it.", ingredientName, this);
            addIngredient(new Ingredient(ingredientName, 1));
        }
    }

}
