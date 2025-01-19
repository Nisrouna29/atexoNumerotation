package com.test.technique.numerotation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a user's personal information.
 * <p>
 * This class holds the basic information of a user, including their first name, last name,
 * and birthdate. It is used for generating numerotation or other user-related tasks.
 * </p>
 */
@Getter
@Setter
public class UserInfo {

    /**
     * The first name of the user.
     * <p>
     * This is the given name of the user, typically provided at birth or chosen by the user.
     * </p>
     */
    private String firstname;

    /**
     * The last name (family name) of the user.
     * <p>
     * This is the user's family name, typically inherited from one or both parents.
     * </p>
     */
    private String lastname;

    /**
     * The birthdate of the user.
     * <p>
     * The user's birthdate, represented as a {@link LocalDate}.
     * </p>
     */
    private LocalDate birthdate;

}
