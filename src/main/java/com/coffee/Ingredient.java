package com.coffee;

public final class Ingredient {

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
        if (!name.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Invalid ingredient name (name not valid)");
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

}
