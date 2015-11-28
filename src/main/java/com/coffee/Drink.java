package com.coffee;

import com.coffee.utils.Validator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Drink {

    private String name;

    private Set<Ingredient> ingredients;

    public Drink() {
        ingredients = new HashSet<>();
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
        return Objects.equals(this.ingredients, other.ingredients);
    }

}
