package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.Stacktrace;
import com.tokmakov.fuzzing_pantry.repository.StacktraceRepository;
import com.tokmakov.fuzzing_pantry.service.StacktraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StacktraceServiceImpl implements StacktraceService {

    @Autowired
    private StacktraceRepository stacktraceRepository;

    @Override
    public Stacktrace findOrCreate(Stacktrace stacktrace) {
        return stacktraceRepository
                .findByTraceAndLabel(stacktrace.getTrace(), stacktrace.getLabel())
                .orElseGet(() -> {
                    return stacktraceRepository.save(stacktrace);
                });
    }
}
