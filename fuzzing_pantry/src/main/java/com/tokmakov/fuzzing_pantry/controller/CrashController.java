package com.tokmakov.fuzzing_pantry.controller;

import com.tokmakov.fuzzing_pantry.dto.CrashCreationDto;
import com.tokmakov.fuzzing_pantry.dto.CrashViewDto;
import com.tokmakov.fuzzing_pantry.mapper.CrashDtoModelMapper;
import com.tokmakov.fuzzing_pantry.model.Crash;
import com.tokmakov.fuzzing_pantry.model.Issue;
import com.tokmakov.fuzzing_pantry.service.CrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/crash")
public class CrashController {

    @Autowired
    private CrashService crashService;

    @PostMapping("/save_new_crash")
    @ResponseBody
    public ResponseEntity<CrashViewDto> saveCrash(@RequestBody CrashCreationDto crashCreationDto) {
        Crash crashFromDto = CrashDtoModelMapper.toModel(crashCreationDto);
        Optional<Crash> optionalCrash = crashService.findByTargetNameAndStacktrace(
                crashFromDto.getTargetName(),
                crashFromDto.getStacktrace());
        if (optionalCrash.isPresent()) {
            Crash crash = crashService.updateLastReproduceDate(optionalCrash.get());
            if (!crash.getArchitectures().containsAll(crashFromDto.getArchitectures())) {
                crash = crashService.updateArchitectures(crash, crashFromDto.getArchitectures());
            }
            if (!crashFromDto.getArtifacts().isEmpty()) {
                crash = crashService.updateArtifacts(crash, crashFromDto.getArtifacts());
            }
            crash = crashService.updateStatus(crash, crash.getFirstReproduceDate());
            return ResponseEntity.status(HttpStatus.OK).body(CrashDtoModelMapper.toDto(crash));
        } else {
            Crash crash = crashService.saveNew(crashFromDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(CrashDtoModelMapper.toDto(crash));
        }
    }

    @GetMapping("/get_by_id")
    @ResponseBody
    public ResponseEntity<CrashViewDto> getCrashById(@RequestParam(value = "id") Integer id) {
        Optional<Crash> crash = crashService.findById(id);
        if (crash.isEmpty()) {
            throw new EntityNotFoundException(String.format("Not found crash with id: %s", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CrashDtoModelMapper.toDto(crash.get()));
    }

    @GetMapping("/get_by_target_stacktrace_label")
    @ResponseBody
    public ResponseEntity<CrashViewDto> getCrashByStacktraceLabel(@RequestParam(value = "target") String target,
                                                       @RequestParam(value = "stacktrace_label") String stacktraceLabel) {
        Optional<Crash> crash = crashService.findByTargetNameAndStacktraceLabel(target, stacktraceLabel);
        if (crash.isEmpty()) {
            throw new EntityNotFoundException(String.format("Not found crash with target: %s and stacktraceLabel: %s", target, stacktraceLabel));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CrashDtoModelMapper.toDto(crash.get()));
    }

    @PostMapping("/add_issue_for_crash")
    @ResponseBody
    public ResponseEntity<String> addIssue(@RequestParam(value = "crash_id") Integer id,
                                           @RequestParam(value = "name") String name,
                                           @RequestParam(value = "link") String link) {
        Optional<Crash> optionalCrash = crashService.findById(id);
        if (optionalCrash.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Crash with id %s not found", id));
        }
        crashService.updateIssue(optionalCrash.get(), new Issue(name, link));
        return ResponseEntity.status(200).body("Issue was added");
    }
}
