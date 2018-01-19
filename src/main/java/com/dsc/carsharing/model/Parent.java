package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("PARENT")
public class Parent extends User {

    private String gender;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "parent_children",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "children_id")
    )
    private Set<Children> children;

    @OneToMany(mappedBy = "parent")
    private List<Car> cars;

}
