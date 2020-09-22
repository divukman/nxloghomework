package com.dimitar.nxlog.homework.db.entities;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;

@Entity
@Data
public class Module extends AbstractEntity {
    @NaturalId
    @ManyToOne(cascade = DETACH)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @NaturalId
    @Column(name = "name", length = 128)
    private String name;

    @Column(nullable = false, length = 31)
    @Enumerated(EnumType.STRING)
    private ModuleType type;
}