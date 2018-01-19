package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    Excursion findByDestination(String destination);
}
