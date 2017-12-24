package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String model;

    private Integer seats;

    @ManyToOne
    private Parent parent;

}
