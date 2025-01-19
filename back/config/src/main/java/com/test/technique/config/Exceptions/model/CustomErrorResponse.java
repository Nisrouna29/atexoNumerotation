package com.test.technique.config.Exceptions.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {
    private Date timestamp;
    private String message;
    private int status;

}

