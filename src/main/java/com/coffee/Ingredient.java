package com.coffee;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ingredient {

    private static final Logger logger = LoggerFactory.getLogger(Ingredient.class);

    private String name;

    private int quantity;

    private final static int MAX_QUANTITY = 100;
    private final static int MAX_NAME_LENGTH = 30;

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
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (null == name || "".equals(name)) {
            throw new IllegalArgumentException("Invalid ingredient name (name null or empty)");
        }
        if (MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException("Invalid ingredient name (name too long)");
        }
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid ingredient name (name not valid)");
        }
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
        return "Ingredient: " + this.name + ", Quantity: " + quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.quantity;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return this.quantity == other.quantity;
    }

}
