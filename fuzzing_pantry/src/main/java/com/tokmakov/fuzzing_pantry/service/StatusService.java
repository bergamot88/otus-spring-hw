package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusService {
    Status findByName(String name);
}
