package it.personal.Components.machines;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineWithComponentsRequest {
    private String name;
    private Double price;
    private List<ComponentWithQuantityDTO> components;
}
