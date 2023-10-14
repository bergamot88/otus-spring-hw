package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.Artifact;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactService {
    Artifact findOrCreate(String name, String link);
}
