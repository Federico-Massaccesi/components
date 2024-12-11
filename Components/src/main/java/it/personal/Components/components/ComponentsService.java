package it.personal.Components.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentsService {

    @Autowired
    private ComponentsRepository componentRepository;

    public Components saveComponent(Components component) {
        return componentRepository.save(component);
    }

    public Components getComponentById(Long id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Component not found with id: " + id));
    }

    public List<Components> getAllComponents() {
        return componentRepository.findAll();
    }

    public void deleteComponentById(Long id) {
        componentRepository.deleteById(id);
    }
}
