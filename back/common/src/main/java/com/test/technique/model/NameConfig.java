package com.test.technique.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NameConfig extends Config {
    private int length;

    public NameConfig(String criterionType, int orderIndex, int length, String prefix, String suffix) {
        super(criterionType, orderIndex, prefix, suffix);
        this.length = length;
    }

}

