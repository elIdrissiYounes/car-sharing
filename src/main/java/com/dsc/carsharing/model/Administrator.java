package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Administrator extends User {

    private String firstName;

    private String lastName;

    private String email;

}
