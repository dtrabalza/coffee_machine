package com.coffee.core.utils;

import com.coffee.utils.Validator;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void validStringPassesValidation() {
        Validator.validateString("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonAlphabeticStringFails() {
        Validator.validateString("53426781");
    }

    @Test(expected = IllegalArgumentException.class)
    public void alphanumericStringFails() {
        Validator.validateString("h725");
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialCharacterStringFails1() {
        Validator.validateString("\"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialCharacterStringFails2() {
        Validator.validateString(".");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nukkStringFails() {
        Validator.validateString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyStringFails() {
        Validator.validateString("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void blankStringFails() {
        Validator.validateString(" ");
    }

}
