package com.tokmakov.fuzzing_pantry.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "crash")
@RequiredArgsConstructor
@NoArgsConstructor
public class Crash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "target_name")
    private String targetName;

    @NonNull
    @Column(name = "first_reproduce_date")
    private Timestamp firstReproduceDate;

    @NonNull
    @Column(name = "last_reproduce_date")
    private Timestamp lastReproduceDate;

    @NonNull
    @JoinColumn(name = "status_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Status status;

    @NonNull
    @JoinColumn(name = "stacktrace_id")
    @OneToOne(fetch=FetchType.LAZY)
    private Stacktrace stacktrace;

    @NonNull
    @ManyToMany
    @JoinTable(name = "crash_artifact",
            joinColumns = @JoinColumn(name = "crash_id"),
            inverseJoinColumns = @JoinColumn(name = "artifact_id"))
    private Set<Artifact> artifacts;

    @NonNull
    @ManyToMany
    @JoinTable(name = "crash_architecture",
            joinColumns = @JoinColumn(name = "crash_id"),
            inverseJoinColumns = @JoinColumn(name = "architecture_id"))
    private Set<Architecture> architectures;

    @NonNull
    @ManyToMany
    @JoinTable(name = "crash_issue",
            joinColumns = @JoinColumn(name = "crash_id"),
            inverseJoinColumns = @JoinColumn(name = "issue_id"))
    private Set<Issue> issues;

    public Crash(Crash crash) {
        this.id = crash.getId();
        this.targetName = crash.getTargetName();
        this.firstReproduceDate = crash.getFirstReproduceDate();
        this.lastReproduceDate = crash.getLastReproduceDate();
        this.status = crash.getStatus();
        this.stacktrace = crash.getStacktrace();
        this.artifacts = crash.getArtifacts();
        this.architectures = crash.getArchitectures();
        this.issues = crash.getIssues();
    }

//    public void addArchitecture(Architecture architecture) {
//        architectures.add(architecture);
//    }
}
