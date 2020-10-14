package com.dimitar.nxlog.homework.db.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class AgentTemplate extends AbstractEntity {
    @Column(length = 1024)
    @JsonProperty
    private String description;


    @ManyToMany(mappedBy = "subTemplates", fetch = FetchType.EAGER)
    private Set<Agent> agents = new HashSet<>();

}
