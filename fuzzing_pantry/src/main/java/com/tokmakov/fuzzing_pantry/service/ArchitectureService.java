package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.Architecture;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectureService {
    Architecture findOrCreate(String name);
}
