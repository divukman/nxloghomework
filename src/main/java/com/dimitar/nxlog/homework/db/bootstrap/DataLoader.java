package com.dimitar.nxlog.homework.db.bootstrap;

import com.dimitar.nxlog.homework.db.entities.*;
import com.dimitar.nxlog.homework.db.entities.Module;
import com.dimitar.nxlog.homework.db.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final AgentRepository agentRepository;
    private final ModuleRepository moduleRepository;
    private final RouteRepository routeRepository;
    private final ModuleRouteRepository moduleRouteRepository;
    private final HibernateRepository hibernateRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");


        final Agent agent1 = Agent.builder()
                .name("Agent #1")
                .routes(new LinkedHashSet<>())
                .type(AgentType.FIELD)
                .globalConfig("config #1")
                .build();

        final Agent agent2 = Agent.builder()
                .name("Agent #2")
                .routes(new LinkedHashSet<>())
                .type(AgentType.FIELD)
                .globalConfig("config #1")
                .build();

        agentRepository.save(agent1);
        agentRepository.save(agent2);

        final Route route1 = Route.builder()
                .agent(agent1)
                .priority(10)
                .name("Route #1")
                .build();

        final Route route2 = Route.builder()
                .agent(agent1)
                .priority(20)
                .name("Route #2")
                .build();

        final Route route3 = Route.builder()
                .agent(agent1)
                .priority(10)
                .name("Route #3")
                .build();

        final Route route4 = Route.builder()
                .agent(agent1)
                .priority(10)
                .name("Route #4")
                .build();

        final Route route5 = Route.builder()
                .agent(agent2)
                .priority(10)
                .name("Route #5")
                .build();

        final Route route6 = Route.builder()
                .agent(agent2)
                .priority(10)
                .name("Route #6")
                .build();

        routeRepository.save(route1);
        routeRepository.save(route2);
        routeRepository.save(route3);
        routeRepository.save(route4);
        routeRepository.save(route5);
        routeRepository.save(route6);

        final Module module1 = Module.builder()
                .agent(agent1)
                .name("Module #1")
                .type(ModuleType.INPUT)
                .build();

        final Module module2 = Module.builder()
                .agent(agent1)
                .name("Module #2")
                .type(ModuleType.OUTPUT)
                .build();

        final Module module3 = Module.builder()
                .agent(agent1)
                .name("Module #3")
                .type(ModuleType.EXTENSION)
                .build();

        final Module module4 = Module.builder()
                .agent(agent2)
                .name("Module #4")
                .type(ModuleType.INPUT)
                .build();

        final Module module5 = Module.builder()
                .agent(agent1)
                .name("Module #5")
                .type(ModuleType.INPUT)
                .build();

        final Module module6 = Module.builder()
                .agent(agent1)
                .name("Module #6")
                .type(ModuleType.PROCESSOR)
                .build();

        moduleRepository.save(module1);
        moduleRepository.save(module2);
        moduleRepository.save(module3);
        moduleRepository.save(module4);
        moduleRepository.save(module5);
        moduleRepository.save(module6);

        ModuleRoute moduleRoute1 = ModuleRoute.builder()
                .route(route1)
                .module(module1) // INPUT
                .build();

        ModuleRoute moduleRoute2 = ModuleRoute.builder()
                .route(route1)
                .module(module3) // EXTENSION
                .build();

        ModuleRoute moduleRoute3 = ModuleRoute.builder()
                .route(route1)
                .module(module2) // OUTPUT
                .build();

        ModuleRoute moduleRoute4 = ModuleRoute.builder()
                .route(route2)
                .module(module1) // INPUT
                .build();

        ModuleRoute moduleRoute5 = ModuleRoute.builder()
                .route(route3)
                .module(module3) // EXTENSION
                .build();

        ModuleRoute moduleRoute6 = ModuleRoute.builder()
                .route(route1)
                .module(module6) // PROCESSOR
                .build();

        moduleRouteRepository.save(moduleRoute1);
        moduleRouteRepository.save(moduleRoute2);
        moduleRouteRepository.save(moduleRoute3); // Complete route
        moduleRouteRepository.save(moduleRoute4);
        moduleRouteRepository.save(moduleRoute5);
        moduleRouteRepository.save(moduleRoute6);

        log.info("Loading data... done ...");

        System.out.println("Getting modules for completed routes for Agent #1");

        Set<Module> modules = moduleRepository.getModulesIncludedInCompleteRoutes(agent1.getId());

        System.out.println("HQL version:");
        System.out.println("----------------------------------------------------------------------");
        modules.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------");

        final Set<Module> hashSet = hibernateRepository.getModulesIncludedInCompleteRoutes(agent1.getId());
        System.out.println("SQL version:");
        System.out.println("----------------------------------------------------------------------");
        hashSet.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------");
    }
}
