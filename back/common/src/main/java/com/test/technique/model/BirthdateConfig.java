package com.test.technique.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class BirthdateConfig extends Config {
    private String dateFormat;

    public BirthdateConfig(String criterionType,int orderIndex, String prefix, String suffix, String dateFormat) {
        super(criterionType, orderIndex, prefix, suffix);
        this.dateFormat = dateFormat;
    }

}
