package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
