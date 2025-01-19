package com.test.technique.model;

public enum CriterionType {
    FIRSTNAME,
    NAME,
    BIRTHDATE,
    COUNTER;

    public static boolean isValid(String value) {
        for (CriterionType type : values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
