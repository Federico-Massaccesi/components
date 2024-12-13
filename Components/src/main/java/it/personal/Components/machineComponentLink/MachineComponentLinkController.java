package it.personal.Components.machineComponentLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines-component-link")
public class MachineComponentLinkController {
    @Autowired
    private MachineComponentLinkService machineComponentLinkService;

    // Get all MachineComponentLinks
    @GetMapping
    public ResponseEntity<List<MachineComponentLink>> getAll() {
        List<MachineComponentLink> links = machineComponentLinkService.getAll();
        return ResponseEntity.ok(links);
    }

    // Get one MachineComponentLink by ID
    @GetMapping("/{id}")
    public ResponseEntity<MachineComponentLink> getById(@PathVariable Long id) {
        MachineComponentLink link = machineComponentLinkService.getById(id);
        return ResponseEntity.ok(link);
    }

    // Create a new MachineComponentLink
    @PostMapping
    public ResponseEntity<MachineComponentLink> create(@RequestBody MachineComponentLink machineComponentLink) {
        MachineComponentLink createdLink = machineComponentLinkService.create(machineComponentLink);
        return ResponseEntity.ok(createdLink);
    }

    // Delete a MachineComponentLink by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        machineComponentLinkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
