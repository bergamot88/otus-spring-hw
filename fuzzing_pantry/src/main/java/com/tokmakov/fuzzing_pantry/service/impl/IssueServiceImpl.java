package com.tokmakov.fuzzing_pantry.service.impl;

import com.tokmakov.fuzzing_pantry.model.Issue;
import com.tokmakov.fuzzing_pantry.repository.IssueRepository;
import com.tokmakov.fuzzing_pantry.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue findOrCreate(Issue issue) {
        return issueRepository
                .findByNameAndLink(issue.getName(), issue.getLink())
                .orElseGet(() -> {
                    return issueRepository.save(issue);
                });
    }
}
