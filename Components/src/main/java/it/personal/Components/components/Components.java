package it.personal.Components.components;

import it.personal.Components.BaseEntity;
import it.personal.Components.machineComponentLink.MachineComponentLink;
import jakarta.persistence.*;
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

    private Integer totalQuantity;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MachineComponentLink> machines;
}
