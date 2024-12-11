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
    public ResponseEntity<Machines> createMachine(@RequestBody Machines machine) {
        return ResponseEntity.ok(machineService.saveMachine(machine));
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
