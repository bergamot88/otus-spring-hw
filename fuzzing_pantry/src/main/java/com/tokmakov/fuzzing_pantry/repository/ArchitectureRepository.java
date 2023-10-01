package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Architecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchitectureRepository extends JpaRepository<Architecture, Integer> {
    Optional<Architecture> findByName(String name);
}
