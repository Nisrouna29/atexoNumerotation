package com.test.technique.counter.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "counter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Counter {
    @Id
    private String id = "global";
    private int currentValue;
    public Counter(int initialValue) {
        this.currentValue = initialValue;
    }
}


