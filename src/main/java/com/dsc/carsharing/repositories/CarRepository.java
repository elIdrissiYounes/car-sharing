package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByParentUsername(String parentUsername);
}
