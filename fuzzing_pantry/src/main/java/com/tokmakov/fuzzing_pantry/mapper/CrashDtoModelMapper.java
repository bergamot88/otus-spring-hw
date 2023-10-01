package com.tokmakov.fuzzing_pantry.mapper;

import com.tokmakov.fuzzing_pantry.dto.CrashCreationDto;
import com.tokmakov.fuzzing_pantry.dto.CrashViewDto;
import com.tokmakov.fuzzing_pantry.model.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CrashDtoModelMapper {
    public static Crash toModel(CrashCreationDto crashCreationDto) {
        Set<Architecture> architectures = new HashSet<>();
        Set<Artifact> artifacts = new HashSet<>();
        for (String architecture : crashCreationDto.getArchitectures()) {
            architectures.add(new Architecture(architecture));
        }
        if (crashCreationDto.getArtifactName() != null & crashCreationDto.getArtifactLink() != null) {
            artifacts = Set.of(new Artifact(crashCreationDto.getArtifactName(), crashCreationDto.getArtifactLink()));
        }
        return new Crash(crashCreationDto.getTargetName(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Status(),
                new Stacktrace(crashCreationDto.getStacktrace(), crashCreationDto.getStacktraceLabel()),
                artifacts,
                architectures,
                new HashSet<>());
    }

    public static CrashViewDto toDto(Crash crash) {
        Set<String> architectures = crash.getArchitectures().stream()
                .map(Architecture::getName)
                .collect(Collectors.toSet());
        Set<Artifact> artifacts = crash.getArtifacts().stream()
                .map(f -> new Artifact(f.getName(), f.getLink()))
                .collect(Collectors.toSet());
        Set<Issue> issues = crash.getIssues().stream()
                .map(f -> new Issue(f.getName(), f.getLink()))
                .collect(Collectors.toSet());
        return new CrashViewDto(
                crash.getId(),
                crash.getTargetName(),
                crash.getFirstReproduceDate().toString(),
                crash.getLastReproduceDate().toString(),
                crash.getStatus().getName(),
                crash.getStacktrace().getLabel(),
                crash.getStacktrace().getTrace(),
                architectures,
                artifacts,
                issues);
    }
}
