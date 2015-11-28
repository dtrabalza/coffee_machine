package com.coffee;

import com.coffee.utils.Validator;
import java.util.ArrayList;
import java.util.List;

public final class Drink {

    private String name;

    private List<Ingredient> ingredients;

    public Drink() {
        ingredients = new ArrayList<>();
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            throw new IllegalArgumentException("Cannot add twice the same ingredient");
        }

        ingredients.add(ingredient);
    }

}
