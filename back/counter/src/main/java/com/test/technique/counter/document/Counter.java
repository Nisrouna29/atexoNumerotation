package com.test.technique.counter.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a counter document in the MongoDB collection "counter".
 *
 * This class is used to store the current counter value. It is annotated with
 * {@link Document} to indicate that it corresponds to a collection in MongoDB.
 * The counter is identified by a fixed id ("global"), and the value of the counter
 * is stored in the field {@link #currentValue}.
 */
@Document(collection = "counter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Counter {

    /**
     * The unique identifier for this counter document.
     * This is fixed to the value "global" since there is only one counter in the system.
     */
    @Id
    private String id = "global";

    /**
     * The current value of the counter.
     * This field represents the numeric value of the counter that will be incremented or retrieved.
     */
    private int currentValue;

    /**
     * Constructor to initialize the counter with a specific value.
     *
     * This constructor allows initializing the counter with a custom value when creating a new counter document.
     *
     * @param initialValue The initial value to set for the counter.
     */
    public Counter(int initialValue) {
        this.currentValue = initialValue;
    }
}
