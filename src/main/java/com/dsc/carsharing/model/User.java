package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public abstract class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

}
