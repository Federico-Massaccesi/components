package it.personal.Components.machines;


import it.personal.Components.BaseEntity;
import it.personal.Components.components.Components;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "machines")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Machines extends BaseEntity {

    private String name;

    private Double price;

    @ManyToMany
    @JoinTable(
            name = "machine_component", // Nome della tabella di join
            joinColumns = @JoinColumn(name = "machine_id"), // Colonna per Machines
            inverseJoinColumns = @JoinColumn(name = "component_id") // Colonna per Components
    )
    private List<Components> linkedComponents;

}
