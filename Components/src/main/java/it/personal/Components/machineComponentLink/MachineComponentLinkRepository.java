package it.personal.Components.machineComponentLink;

import it.personal.Components.machines.Machines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineComponentLinkRepository extends JpaRepository<MachineComponentLink,Long> {

    void deleteByComponentId(Long componentId);

    List<MachineComponentLink> findByMachineId(Long machineId);

}
