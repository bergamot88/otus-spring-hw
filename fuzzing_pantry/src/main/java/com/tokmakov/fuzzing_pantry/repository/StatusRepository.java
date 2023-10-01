package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByName(String name);
}
