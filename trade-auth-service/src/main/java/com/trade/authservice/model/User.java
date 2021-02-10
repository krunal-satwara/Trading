package com.trade.authservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class User {

    private String name;
    private String password;

}
