package com.dsc.carsharing.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Excursion {

    @Id
    @GeneratedValue
    private Long id;

    private String destination;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToMany
    @JoinTable(
            name = "excursion_group",
            joinColumns = @JoinColumn(name = "excursion_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;

    @OneToMany(mappedBy = "excursion")
    private List<Proposal> proposals;

}
