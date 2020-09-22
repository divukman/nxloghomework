package com.dimitar.nxlog.homework.db.repositories;

import com.dimitar.nxlog.homework.db.entities.AgentType;
import com.dimitar.nxlog.homework.db.entities.ModuleType;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class HibernateRepository {

    private SessionFactory sessionFactory;
    private AgentRepository agentRepository;

    @Autowired
    public HibernateRepository(EntityManagerFactory factory, AgentRepository agentRepository) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
        this.agentRepository = agentRepository;
    }

    /**
     * Returns all modules for agent which are incllded in a complete route.
     * A complete route contains at least 1 INPUT module and at least 1 OUTPUT module.
     */
    public Set<com.dimitar.nxlog.homework.db.entities.Module> getModulesIncludedInCompleteRoutes(Long agentId) {
        final HashSet<com.dimitar.nxlog.homework.db.entities.Module> hashSet = new HashSet<>();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("" +
                "SELECT AGENT_ID, NAME, TYPE FROM MODULE \n" +
                "INNER JOIN MODULE_ROUTE ON MODULE.ID = MODULE_ROUTE.MODULE_ID\n" +
                "where agent_id = ?\n" +
                "and ROUTE_ID in (\n" +
                "\t\tSELECT ROUTE_ID  as id FROM ROUTE \n" +
                "\tINNER JOIN MODULE_ROUTE ON ROUTE.ID = MODULE_ROUTE.ROUTE_ID\n" +
                "\tINNER JOIN MODULE ON MODULE_ROUTE.MODULE_ID = MODULE.ID\n" +
                "\tWHERE MODULE.TYPE LIKE '%INPUT%'\n" +
                ")\n" +
                "and ROUTE_ID in (\n" +
                "\t\tSELECT ROUTE_ID  as id FROM ROUTE \n" +
                "\tINNER JOIN MODULE_ROUTE ON ROUTE.ID = MODULE_ROUTE.ROUTE_ID\n" +
                "\tINNER JOIN MODULE ON MODULE_ROUTE.MODULE_ID = MODULE.ID\n" +
                "\tWHERE MODULE.TYPE LIKE '%OUTPUT%'\n" +
                ")")
                .setParameter(1,agentId);

        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            final com.dimitar.nxlog.homework.db.entities.Module module = new com.dimitar.nxlog.homework.db.entities.Module();

            final Long resAgentId = Long.parseLong(row[0].toString());
            module.setId(resAgentId);
            module.setName(row[1].toString());
            module.setType(ModuleType.valueOf(row[2].toString()));
            module.setAgent(agentRepository.findById(resAgentId).orElse(null));

            //System.out.println(module);
            hashSet.add(module);
        }
        return hashSet;
    }

}
