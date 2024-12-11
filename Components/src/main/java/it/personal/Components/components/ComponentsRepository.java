package it.personal.Components.components;

import it.personal.Components.machines.Machines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentsRepository extends JpaRepository<Components,Long> {
}
