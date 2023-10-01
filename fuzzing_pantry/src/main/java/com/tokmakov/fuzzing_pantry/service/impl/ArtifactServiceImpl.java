package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.Artifact;
import com.tokmakov.fuzzing_pantry.repository.ArtifactRepository;
import com.tokmakov.fuzzing_pantry.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtifactServiceImpl implements ArtifactService {

    @Autowired
    private ArtifactRepository artifactRepository;

    @Override
    public Artifact findOrCreate(String name, String link) {
        return artifactRepository
                .findByNameAndLink(name, link)
                .orElseGet(() -> {
                    return artifactRepository.save(new Artifact(name, link));
                });
    }
}
