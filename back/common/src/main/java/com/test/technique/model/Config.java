package com.test.technique.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FirstNameConfig.class, name = "FirstNameConfig"),
        @JsonSubTypes.Type(value = NameConfig.class, name = "NameConfig"),
        @JsonSubTypes.Type(value = BirthdateConfig.class, name = "BirthdateConfig"),
        @JsonSubTypes.Type(value = CounterConfig.class, name = "CounterConfig")
})
public  class Config {
    private String criterionType;
    private int orderIndex;
    private String prefix;
    private String suffix;

}



