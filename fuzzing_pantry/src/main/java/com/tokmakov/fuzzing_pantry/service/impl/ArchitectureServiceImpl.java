package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.Architecture;
import com.tokmakov.fuzzing_pantry.repository.ArchitectureRepository;
import com.tokmakov.fuzzing_pantry.service.ArchitectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchitectureServiceImpl implements ArchitectureService {

    @Autowired
    private ArchitectureRepository architectureRepository;

    @Override
    public Architecture findOrCreate(String name) {
        return architectureRepository
                .findByName(name)
                .orElseGet(() -> {
                    return architectureRepository.save(new Architecture(name));
                });
    }
}
