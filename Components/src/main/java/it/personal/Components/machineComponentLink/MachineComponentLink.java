package it.personal.Components.machineComponentLink;

import it.personal.Components.BaseEntity;
import it.personal.Components.components.Components;
import it.personal.Components.machines.Machines;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "machine_component")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class MachineComponentLink extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machines machine;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "component_id", nullable = false)
    private Components component;

    private Integer quantity;
}
