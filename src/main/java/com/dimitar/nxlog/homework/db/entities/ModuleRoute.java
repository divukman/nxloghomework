package com.dimitar.nxlog.homework.db.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
public class ModuleRoute extends AbstractEntity {
    @NaturalId
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "route_id")
    private Route route;

    @NaturalId
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "module_id")
    private Module module;
}
