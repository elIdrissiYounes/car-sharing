package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent extends User {

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "parent_children",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "children_id")
    )
    private List<Children> children;

    @OneToMany(mappedBy = "parent")
    private List<Car> cars;

}
