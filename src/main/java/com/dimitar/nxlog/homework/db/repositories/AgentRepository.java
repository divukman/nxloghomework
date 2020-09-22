package com.dimitar.nxlog.homework.db.repositories;

import com.dimitar.nxlog.homework.db.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
