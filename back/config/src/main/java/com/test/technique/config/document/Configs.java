package com.test.technique.config.document;


import com.test.technique.model.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "configs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configs {
    @Id
    private String id = "configs";
    List<Config> configs;
}



