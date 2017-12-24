package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Excursion {

    @Id
    @GeneratedValue
    private Long id;

    private String destination;

    private String description;

    private Date date;

    @ManyToMany
    @JoinTable(
            name = "excursion_group",
            joinColumns = @JoinColumn(name = "excursion_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

}
