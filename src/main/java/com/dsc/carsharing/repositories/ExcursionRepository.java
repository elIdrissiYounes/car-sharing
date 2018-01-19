package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    List<Excursion> findByGroupsIn(List<Group> groups);
}
