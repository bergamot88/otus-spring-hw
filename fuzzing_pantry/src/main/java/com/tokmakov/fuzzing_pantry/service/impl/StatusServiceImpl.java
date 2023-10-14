package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.Status;
import com.tokmakov.fuzzing_pantry.repository.StatusRepository;
import com.tokmakov.fuzzing_pantry.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name);
    }
}
