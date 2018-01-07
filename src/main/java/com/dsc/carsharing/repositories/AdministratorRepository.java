package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
