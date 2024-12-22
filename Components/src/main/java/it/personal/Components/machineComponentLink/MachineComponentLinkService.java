package it.personal.Components.machineComponentLink;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MachineComponentLinkService {

    @Autowired
    private MachineComponentLinkRepository machineComponentLinkRepository;

    public List<MachineComponentLink> getAll() {
        return machineComponentLinkRepository.findAll();
    }

    public MachineComponentLink getById(Long id) {
        return machineComponentLinkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MachineComponentLink not found with id: " + id));
    }

    public MachineComponentLink create(MachineComponentLink machineComponentLink) {
        return machineComponentLinkRepository.save(machineComponentLink);
    }

    public void deleteById(Long id) {
        if (!machineComponentLinkRepository.existsById(id)) {
            throw new EntityNotFoundException("MachineComponentLink not found with id: " + id);
        }
        machineComponentLinkRepository.deleteById(id);
    }
    @Transactional
    public void deleteByComponentId(Long componentId) {
        machineComponentLinkRepository.deleteByComponentId(componentId);
    }
}
