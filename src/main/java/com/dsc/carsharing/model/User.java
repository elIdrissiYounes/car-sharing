package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

}
