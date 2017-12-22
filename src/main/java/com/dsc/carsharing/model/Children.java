package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Children {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "parents_children",
            joinColumns = @JoinColumn(
                    name = "children_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "parent_id", referencedColumnName = "id"))
    private List<Parent> parents;

    @ManyToOne
    private Group group;

}
