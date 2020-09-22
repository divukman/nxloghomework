package com.dimitar.nxlog.homework.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ModuleRepository extends JpaRepository<com.dimitar.nxlog.homework.db.entities.Module, Long> {
}
