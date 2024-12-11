package it.personal.Components.components;

import it.personal.Components.BaseEntity;
import it.personal.Components.machines.Machines;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "components")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Components extends BaseEntity {

    private String name;

    private Integer quantity;

    @ManyToMany(mappedBy = "linkedComponents")
    private List<Machines> linkedMachines;}
