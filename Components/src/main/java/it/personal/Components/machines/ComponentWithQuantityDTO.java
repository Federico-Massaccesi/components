package it.personal.Components.machines;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentWithQuantityDTO {
    private Long componentId;
    private Integer quantity;
}
