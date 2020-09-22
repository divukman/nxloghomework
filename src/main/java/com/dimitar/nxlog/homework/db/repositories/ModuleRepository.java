package com.dimitar.nxlog.homework.db.repositories;

import com.dimitar.nxlog.homework.db.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ModuleRepository extends JpaRepository<com.dimitar.nxlog.homework.db.entities.Module, Long> {

    @Query("select moduleRoute.module\n" +
            "from ModuleRoute as moduleRoute\n" +
            "where moduleRoute.module.agent.id = :agentIdParam\n" +
            "and moduleRoute.route in\n" +
            "(select mr1.route\n" +
            "from ModuleRoute as mr1\n" +
            "where mr1.module.type = 'INPUT')\n" +
            "and moduleRoute.route in\n" +
            "(select mr2.route\n" +
            "from ModuleRoute as mr2\n" +
            "where mr2.module.type = 'OUTPUT')")
    Set<Module> getModulesIncludedInCompleteRoutes(Long agentIdParam);
}
