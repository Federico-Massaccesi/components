package it.personal.Components.machines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachinesController {

    @Autowired
    private MachinesService machineService;

    @PostMapping
    public ResponseEntity<Machines> createMachine(@RequestBody MachineWithComponentsRequest request) {
        Machines machine = machineService.createMachineWithComponents(request);
        return ResponseEntity.ok(machine);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Machines> getMachineById(@PathVariable Long id) {
        return ResponseEntity.ok(machineService.getMachineById(id));
    }

    @GetMapping
    public ResponseEntity<List<Machines>> getAllMachines() {
        return ResponseEntity.ok(machineService.getAllMachines());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        machineService.deleteMachineById(id);
        return ResponseEntity.noContent().build();
    }
}
