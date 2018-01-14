package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled = true;

}
