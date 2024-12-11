package it.personal.Components.machines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachinesService {

    @Autowired
    private MachinesRepository machineRepository;

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
}
