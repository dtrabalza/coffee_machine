package com.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.info("Coffee machine started");
		System.out.println("Super cool coffee machine ready to use!");

		logger.info("Coffee machine ready");
	}

}
