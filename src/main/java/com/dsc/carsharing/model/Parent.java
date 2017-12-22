package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent extends User {

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    @ManyToOne
    private List<Car> cars;

}
