package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Crash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CrashRepository extends JpaRepository<Crash, Integer> {
    Optional<Crash> findByTargetNameAndStacktraceLabel(String targetName, String label);
    List<Crash> findByTargetName(String targetName);
    Optional<Crash> findById(Integer id);
}
