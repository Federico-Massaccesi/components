package it.personal.Components.machines;


import it.personal.Components.BaseEntity;
import it.personal.Components.machineComponentLink.MachineComponentLink;
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

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MachineComponentLink> components;

}
