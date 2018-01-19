package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Children {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;

    @ManyToOne
    private Group group;

    public Children() {
        parents = new HashSet<>();
    }
}
