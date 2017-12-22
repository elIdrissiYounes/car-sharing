package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Proposal {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Excursion excursion;

    @ManyToOne
    private Parent parent;

    private String departurePlace;

    private Date departureTime;

}
