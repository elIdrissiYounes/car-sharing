package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findByUsername(String username);
}
