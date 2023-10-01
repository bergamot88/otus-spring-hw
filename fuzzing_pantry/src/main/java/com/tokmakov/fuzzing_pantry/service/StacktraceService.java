package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.Stacktrace;
import org.springframework.stereotype.Repository;

@Repository
public interface StacktraceService {
    Stacktrace findOrCreate(Stacktrace stacktrace);
}
