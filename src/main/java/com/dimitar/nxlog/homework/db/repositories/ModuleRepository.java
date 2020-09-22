package com.dimitar.nxlog.homework.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ModuleRepository extends JpaRepository<com.dimitar.nxlog.homework.db.entities.Module, Long> {

    @Query("from Module as module")
    Set<com.dimitar.nxlog.homework.db.entities.Module> getModulesIncludedInCompleteRoutes(Long agentId);
}
