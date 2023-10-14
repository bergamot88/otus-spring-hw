package com.tokmakov.fuzzing_pantry.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CrashCreationDto {
    @NonNull
    private String targetName;
    @NonNull
    private String stacktrace;
    @NonNull
    private String stacktraceLabel;
    @NonNull
    private String[] architectures;
    private String artifactName;
    private String artifactLink;
}
