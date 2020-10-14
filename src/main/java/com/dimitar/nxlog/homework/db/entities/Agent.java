package com.dimitar.nxlog.homework.db.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent extends AbstractEntity {
    @Column(name = "name", length = 127, nullable = false, unique = true)
    protected String name;

    @Column(nullable = false, length = 31)
    @Enumerated(EnumType.STRING)
    private AgentType type;

    @Column(name = "global_config", length = 32671)
    @Lob
    protected String globalConfig;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    protected Set<Route> routes = new LinkedHashSet<>();


    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    protected Set<Module> modules = new LinkedHashSet<>();



    @ManyToMany(fetch = FetchType.EAGER)//(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "NXAgentSubTemplate",
            joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sub_template_id", referencedColumnName = "id"))
    @OrderColumn
    private List<AgentTemplate> subTemplates = new LinkedList<>();



    @Override
    public String toString() {
        return "Agent{" +
                "name='" + name + '\'' +
                '}';
    }
}
