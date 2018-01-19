package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @DateTimeFormat(pattern = "HH:mm")
    private Date departureTime;

    @ManyToOne
    private Car car;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "proposal_children",
            joinColumns = @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "children_id")
    )
    private Set<Children> children;

    public Proposal() {
        children = new HashSet<>();
    }
}
