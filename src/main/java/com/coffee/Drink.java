package com.coffee;

import com.coffee.utils.Validator;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class Drink {

    private final int MAX_NUMBER_OF_INGREDIENTS = 5;

    private String name;

    private Map<String, Ingredient> ingredientsMap;

    public Drink() {
        ingredientsMap = new LinkedHashMap<>();
    }

    public Drink(Drink copy) {
        this.name = new String(copy.getName());
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
        return ingredientsMap.get(ingredientName);
    }

    void increaseStrength(int value) {
        ingredientsMap.entrySet().iterator().next().getValue().increaseQuantity(value);
    }

    void decreaseStrength(int value) {
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
    
}
