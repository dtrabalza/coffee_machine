package com.coffee;

import com.coffee.utils.Validator;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ingredient {

    private static final Logger logger = LoggerFactory.getLogger(Ingredient.class);

    private String name;

    private int quantity;

    private final static int MAX_QUANTITY = 100;

    public Ingredient() {

    }

    public Ingredient(String name, int quantity) {
        setName(name);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Invalid ingredient quantity");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ingredient: " + this.name + " [" + quantity + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
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
        final Ingredient other = (Ingredient) obj;
        return Objects.equals(this.name, other.name);
    }

    void reduceQuantity(int value) {
        if (value > quantity) {
            throw new IllegalStateException("Cannot reduce " + value + "from the quantity of the ingredient: " + this);
        }
        quantity = quantity - value;
    }

}
