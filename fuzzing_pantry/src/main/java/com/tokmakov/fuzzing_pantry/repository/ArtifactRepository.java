package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtifactRepository extends JpaRepository<Artifact, Integer> {
    Optional<Artifact> findByNameAndLink(String name, String link);
}
