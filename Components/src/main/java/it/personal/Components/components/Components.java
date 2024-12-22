package it.personal.Components.components;

import it.personal.Components.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


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

}
