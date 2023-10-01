package com.tokmakov.fuzzing_pantry.dto;

import com.tokmakov.fuzzing_pantry.model.Artifact;
import com.tokmakov.fuzzing_pantry.model.Issue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CrashViewDto {
    @NonNull
    private Integer id;
    @NonNull
    private String targetName;
    @NonNull
    private String firstReproduceDate;
    @NonNull
    private String lastReproduceDate;
    @NonNull
    private String statusName;
    @NonNull
    private String stacktraceLabel;
    @NonNull
    private String stacktrace;
    @NonNull
    private Set<String> architecture;
    @NonNull
    private Set<Artifact> artifacts;
    @NonNull
    private Set<Issue> issues;
}
