package com.tokmakov.fuzzing_pantry.service;

import com.tokmakov.fuzzing_pantry.model.Issue;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueService {
    Issue findOrCreate(Issue issue);
}
