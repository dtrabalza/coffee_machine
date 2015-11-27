package com.coffee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeMachine {

	private static Logger logger = LoggerFactory.getLogger(CoffeeMachine.class);

	private List<Ingredient> ingredients;

	public CoffeeMachine() {

	}

	public CoffeeMachine(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
