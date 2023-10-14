package com.tokmakov.fuzzing_pantry.repository;

import com.tokmakov.fuzzing_pantry.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Optional<Issue> findByNameAndLink(String name, String link);
}
