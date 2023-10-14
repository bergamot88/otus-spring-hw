package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Stacktrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StacktraceRepository extends JpaRepository<Stacktrace, Integer> {
    Optional<Stacktrace> findByTraceAndLabel(String trace, String label);
}
