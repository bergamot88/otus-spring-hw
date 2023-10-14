package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CrashService {
    Optional<Crash> findById(Integer id);
    Crash saveNew(Crash crash);
    Optional<Crash> findByTargetNameAndStacktraceLabel(String targetName, String stacktraceLabel);
    Optional<Crash> findByTargetNameAndStacktrace(String targetName, Stacktrace stacktrace);
    Crash updateLastReproduceDate(Crash crash);
    Crash updateArchitectures(Crash crash, Set<Architecture> architectures);
    Crash updateArtifacts(Crash crash, Set<Artifact> artifacts);
    Crash updateStatus(Crash crash, Timestamp firstReproduceDate);
    Crash updateIssue(Crash crash, Issue issue);
}
