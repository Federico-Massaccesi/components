package it.personal.Components.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentsController {

    @Autowired
    private ComponentsService componentService;

    @PostMapping
    public ResponseEntity<Components> createComponent(@RequestBody Components component) {
        return ResponseEntity.ok(componentService.saveComponent(component));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Components> getComponentById(@PathVariable Long id) {
        return ResponseEntity.ok(componentService.getComponentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Components>> getAllComponents() {
        return ResponseEntity.ok(componentService.getAllComponents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long id) {
        componentService.deleteComponentById(id);
        return ResponseEntity.noContent().build();
    }
}
