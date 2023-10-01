package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.enums.Statuses;
import com.tokmakov.fuzzing_pantry.model.*;
import com.tokmakov.fuzzing_pantry.repository.CrashRepository;
import com.tokmakov.fuzzing_pantry.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CrashServiceImpl implements CrashService {

    @Autowired
    private CrashRepository crashRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ArchitectureService architectureService;

    @Autowired
    private StacktraceService stacktraceService;

    @Autowired
    private ArtifactService artifactService;

    @Autowired
    private DateService dateService;

    @Autowired
    private IssueService issueService;

    @Override
    public Optional<Crash> findById(Integer id) {
        return crashRepository.findById(id);
    }

    @Override
    public Crash saveNew(Crash crash) {
        Crash crashForSave = new Crash(crash);

        Status status = statusService.findByName(Statuses.NEW.getValue());
        crashForSave.setStatus(status);

        Stacktrace stacktrace = stacktraceService.findOrCreate(crash.getStacktrace());
        crashForSave.setStacktrace(stacktrace);

        Set<Architecture> architectureSet = crash.getArchitectures().stream()
                .map(architecture -> architectureService.findOrCreate(architecture.getName()))
                .collect(Collectors.toSet());
        crashForSave.setArchitectures(architectureSet);

        if (!crash.getArtifacts().isEmpty()) {
            Set<Artifact> artifactSet = crash.getArtifacts().stream()
                    .map(artifact -> artifactService.findOrCreate(artifact.getName(), artifact.getLink()))
                    .collect(Collectors.toSet());
            crashForSave.setArtifacts(artifactSet);
        }

        Timestamp timestamp = dateService.getCurrentTimeStamp();
        crashForSave.setFirstReproduceDate(timestamp);
        crashForSave.setLastReproduceDate(timestamp);
        return crashRepository.save(crashForSave);
    }

    @Override
    public Optional<Crash> findByTargetNameAndStacktraceLabel(String targetName, String stacktraceLabel) {
        return crashRepository.findByTargetName(targetName).stream()
                .filter(crash -> crash.getStacktrace().getLabel().equals(stacktraceLabel))
                .findFirst();
    }

    @Override
    public Optional<Crash> findByTargetNameAndStacktrace(String targetName, Stacktrace stacktrace) {
        return crashRepository.findByTargetName(targetName).stream()
                .filter(crash -> crash.getStacktrace().getLabel().equals(stacktrace.getLabel()) &&
                        crash.getStacktrace().getTrace().equals(stacktrace.getTrace()))
                .findFirst();
    }

    @Override
    public Crash updateLastReproduceDate(Crash crash) {
        Crash crashForSave = new Crash(crash);
        crashForSave.setLastReproduceDate(dateService.getCurrentTimeStamp());
        return crashRepository.save(crashForSave);
    }

    @Override
    public Crash updateArchitectures(Crash crash, Set<Architecture> architectures) {
        Crash crashForSave = new Crash(crash);
        Set<Architecture> architectureSet = architectures.stream()
                .map(architecture -> architectureService.findOrCreate(architecture.getName()))
                .collect(Collectors.toSet());
        crashForSave.getArchitectures().addAll(architectureSet);
        return crashRepository.save(crashForSave);
    }

    @Override
    public Crash updateArtifacts(Crash crash, Set<Artifact> artifacts) {
        Crash crashForSave = new Crash(crash);
        Set<Artifact> artifactSet = artifacts.stream()
                .map(artifact -> artifactService.findOrCreate(artifact.getName(), artifact.getLink()))
                .collect(Collectors.toSet());
        crashForSave.getArtifacts().addAll(artifactSet);
        return crashRepository.save(crashForSave);
    }

    @Override
    public Crash updateStatus(Crash crash, Timestamp firstReproduceDate) {
        Crash crashForSave = new Crash(crash);
        Status status = statusService.findByName(Statuses.NEW.getValue());;

        long diff = dateService.getDifferenceFirstDateAndNow(firstReproduceDate);
        if (diff >= Statuses.OLD.getDays()) {
            status = statusService.findByName(Statuses.OLD.getValue());
        } else if (diff >= Statuses.AVERAGE.getDays()) {
            status = statusService.findByName(Statuses.AVERAGE.getValue());
        }
        crashForSave.setStatus(status);
        return crashRepository.save(crashForSave);
    }

    @Override
    public Crash updateIssue(Crash crash, Issue issue) {
        Crash crashForSave = new Crash(crash);
        Issue issueForSave = issueService.findOrCreate(issue);
        crashForSave.getIssues().add(issueForSave);
        return crashRepository.save(crashForSave);
    }
}
