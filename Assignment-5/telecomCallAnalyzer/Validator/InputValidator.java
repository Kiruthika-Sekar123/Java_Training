package com.telecomCallAnalyzer.util;

import com.telecomCallAnalyzer.exceptions.InvalidInputException;

public class InputValidator {

    public static String validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException("Name must not be empty and should contain only letters.");
        }
        return name.trim();
    }

    public static String validatePhoneNumber(String phone) throws InvalidInputException {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be exactly 10 digits.");
        }
        return phone;
    }

    public static String validateOperator(String operator) throws InvalidInputException {
        if (operator == null || operator.trim().isEmpty() || !operator.matches("[a-zA-Z]+")) {
            throw new InvalidInputException("Operator must contain only letters.");
        }
        return operator.trim();
    }

    public static String validateLocation(String location) throws InvalidInputException {
        if (location == null || location.trim().isEmpty() || !location.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException("Location must contain only letters.");
        }
        return location.trim();
    }
}
