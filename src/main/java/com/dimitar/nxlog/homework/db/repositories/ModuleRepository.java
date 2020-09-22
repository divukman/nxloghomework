package com.dimitar.nxlog.homework.db.repositories;

import com.dimitar.nxlog.homework.db.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ModuleRepository extends JpaRepository<com.dimitar.nxlog.homework.db.entities.Module, Long> {

    @Query("from Module as module\n" +
            "inner join module.agent as agent\n" +
            "where agent.id = :agentIdParam")
    Set<com.dimitar.nxlog.homework.db.entities.Module> getModulesIncludedInCompleteRoutes(Long agentIdParam);

    @Query("select moduleRoute.module\n" +
            "from ModuleRoute as moduleRoute\n" +
            "inner join moduleRoute.route as route\n" +
            "where moduleRoute.module.agent.id = :agentIdParam\n" +
            "and route.id in\n" +
            "(select moduleRoute.route.id\n" +
            "from ModuleRoute as moduleRoute\n" +
            "where moduleRoute.module.type = 'INPUT')\n" +
            "and route.id in\n" +
            "(select moduleRoute.route.id\n" +
            "from ModuleRoute as moduleRoute\n" +
            "where moduleRoute.module.type = 'OUTPUT')")
    List<Module> getModulesIncludedInCompleteRoutes2(Long agentIdParam);

    @Query("select moduleRoute.route.id\n" +
            "from ModuleRoute as moduleRoute\n" +
            "where moduleRoute.module.type = 'INPUT'")
    Set<Long> getRoutes();
}
