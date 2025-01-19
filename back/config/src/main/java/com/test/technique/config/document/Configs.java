package com.test.technique.config.document;

import com.test.technique.model.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents a collection of configuration settings stored in MongoDB.
 *
 * This class is annotated with {@link Document} to define it as a MongoDB document.
 * The collection name in MongoDB is `configs`, and it contains a list of {@link Config} objects
 * which represent individual configuration settings.
 * <p>
 * The {@link Configs} class is used to store all configuration settings together, with the
 * ability to retrieve or update the entire configuration set as a single entity.
 * </p>
 */
@Document(collection = "configs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configs {

    /**
     * The unique identifier for this configuration set.
     * This field is marked with the {@link Id} annotation, indicating that it serves as the
     * primary key for the MongoDB document. The default value is "configs".
     */
    @Id
    private String id = "configs";  // Default ID value for this document

    /**
     * The list of configuration objects that are part of this configuration set.
     * This list contains all the individual configuration settings.
     *
     * @see Config
     */
    private List<Config> configs;
}
