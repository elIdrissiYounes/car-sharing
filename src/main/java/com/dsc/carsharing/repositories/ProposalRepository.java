package com.dsc.carsharing.repositories;

import com.dsc.carsharing.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
