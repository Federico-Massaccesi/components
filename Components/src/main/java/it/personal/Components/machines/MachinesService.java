package it.personal.Components.machines;

import it.personal.Components.components.Components;
import it.personal.Components.components.ComponentsRepository;
import it.personal.Components.components.LinkedComponentDTO;
import it.personal.Components.machineComponentLink.MachineComponentLink;
import it.personal.Components.machineComponentLink.MachineComponentLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachinesService {

    @Autowired
    private MachinesRepository machineRepository;

    @Autowired
    private ComponentsRepository componentsRepository;

    @Autowired
    private MachineComponentLinkRepository machineComponentLinkRepository;

    public Machines saveMachine(Machines machine) {
        return machineRepository.save(machine);
    }

    public MachineResponseDTO getMachineById(Long id) {
        var foundedMachine = machineRepository.findById(id);

        if (foundedMachine.isPresent()) {
            List<MachineComponentLink> links = machineComponentLinkRepository.findByMachineId(id);

            List<LinkedComponentDTO> linkedComponents = links.stream()
                    .map(link -> {
                        Components component = link.getComponent();
                        return new LinkedComponentDTO(component.getName(), link.getQuantity());
                    })
                    .collect(Collectors.toList());

            Machines machine = foundedMachine.get();
            return new MachineResponseDTO(machine.getName(), machine.getPrice(), linkedComponents);
        } else {
            throw new RuntimeException("Machine not found with id " + id);
        }
    }

    public List<Machines> getAllMachines() {
        return machineRepository.findAll();
    }

    public void deleteMachineById(Long id) {
        machineRepository.deleteById(id);
    }


    @Transactional
    public Machines createMachine(MachineRequestDTO machineRequest) {
        // Creare l'entità della macchina
        Machines machine = Machines.builder()
                .withName(machineRequest.getName())
                .withPrice(machineRequest.getPrice())
                .build();
        Machines savedMachine = machineRepository.save(machine);

        for (ComponentQuantityDTO componentRequest : machineRequest.getComponents()) {
            Components component = componentsRepository.findById(componentRequest.getComponentId())
                    .orElseThrow(() -> new IllegalArgumentException("Component not found with ID: " + componentRequest.getComponentId()));

            MachineComponentLink link = MachineComponentLink.builder()
                    .withMachine(savedMachine)
                    .withComponent(component)
                    .withQuantity(componentRequest.getQuantity())
                    .build();
            machineComponentLinkRepository.save(link);
        }

        return savedMachine;
    }

}
