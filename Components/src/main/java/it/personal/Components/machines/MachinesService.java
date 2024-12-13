package it.personal.Components.machines;

import it.personal.Components.components.Components;
import it.personal.Components.components.ComponentsRepository;
import it.personal.Components.machineComponentLink.MachineComponentLink;
import it.personal.Components.machineComponentLink.MachineComponentLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Machines getMachineById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Machine not found with id: " + id));
    }

    public List<Machines> getAllMachines() {
        return machineRepository.findAll();
    }

    public void deleteMachineById(Long id) {
        machineRepository.deleteById(id);
    }


    public Machines createMachineWithComponents(MachineWithComponentsRequest request) {
        // Crea il macchinario
        Machines machine = new Machines();
        machine.setName(request.getName());
        machine.setPrice(request.getPrice());
        Machines savedMachine = machineRepository.save(machine);

        // Associa i componenti e le quantità
        for (ComponentWithQuantityDTO componentRequest : request.getComponents()) {
            Components component = componentsRepository.findById(componentRequest.getComponentId())
                    .orElseThrow(() -> new RuntimeException("Componente non trovato"));

            MachineComponentLink link = new MachineComponentLink();
            link.setMachine(savedMachine);
            link.setComponent(component);
            link.setQuantity(componentRequest.getQuantity());

            // Salva la relazione
            machineComponentLinkRepository.save(link);
        }

        // Ritorna la macchina con i componenti associati
        return savedMachine;
    }

}
