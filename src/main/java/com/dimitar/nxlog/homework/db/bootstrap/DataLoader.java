package com.dimitar.nxlog.homework.db.bootstrap;

import com.dimitar.nxlog.homework.db.entities.Agent;
import com.dimitar.nxlog.homework.db.entities.AgentType;
import com.dimitar.nxlog.homework.db.entities.Route;
import com.dimitar.nxlog.homework.db.repositories.AgentRepository;
import com.dimitar.nxlog.homework.db.repositories.ModuleRepository;
import com.dimitar.nxlog.homework.db.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final AgentRepository agentRepository;
    private final ModuleRepository moduleRepository;
    private final RouteRepository routeRepository;


    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");


        final Agent agent1 = Agent.builder()
                .name("Fox Mulder")
                .routes(new LinkedHashSet<>())
                .type(AgentType.FIELD)
                .globalConfig("FBI/SkinnersOffice")
                .build();

        final Agent agent2 = Agent.builder()
                .name("Dana Scully")
                .routes(new LinkedHashSet<>())
                .type(AgentType.FIELD)
                .globalConfig("FBI/SkinnersOffice")
                .build();




/*        final Route route2 = Route.builder()
                .agent(agent1)
                .name("Field Route No 2")
                .build();

        final Route route3 = Route.builder()
                .agent(agent2)
                .name("Field Route No 3")
                .build();

        final Route route4 = Route.builder()
                .agent(agent2)
                .name("Field Route No 4")
                .build();*/






      //  agent1.getRoutes().add(route1);

        agentRepository.save(agent1);
        agentRepository.save(agent2);

        final Route route1 = Route.builder()
                .agent(agent1)
                .priority(10)
                .name("Field Route No 1")
                .build();

        routeRepository.save(route1);


        log.info("Loading data... done ...");
    }
}
