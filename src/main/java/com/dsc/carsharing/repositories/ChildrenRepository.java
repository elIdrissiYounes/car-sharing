package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Children;
import com.dsc.carsharing.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildrenRepository extends JpaRepository<Children, Long> {

    List<Children> findByParentsIn(List<Parent> parents);
}
