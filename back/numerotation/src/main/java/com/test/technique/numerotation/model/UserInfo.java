package com.test.technique.numerotation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfo {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
}
