package it.personal.Components.machines;

import it.personal.Components.components.LinkedComponentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineResponseDTO {
    private String name;
    private Double price;
    private List<LinkedComponentDTO> components;
}
