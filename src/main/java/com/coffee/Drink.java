package com.coffee;

import com.coffee.utils.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Drink addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            throw new IllegalArgumentException("Cannot add twice the same ingredient");
        }

        ingredients.add(ingredient);
        return this;
    }

    @Override
    public String toString() {
        return "Drink: " + name + " " + ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.ingredients);
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
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }

}
