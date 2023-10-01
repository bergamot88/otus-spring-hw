package com.tokmakov.fuzzing_pantry.controller;

import com.tokmakov.fuzzing_pantry.model.Issue;
import com.tokmakov.fuzzing_pantry.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/add_new")
    @ResponseBody
    public ResponseEntity<String> addIssue(@RequestParam(value = "name") String name,
                                           @RequestParam(value = "link") String link) {
        Issue issue = new Issue(name, link);
        issueService.findOrCreate(issue);
        return ResponseEntity.status(HttpStatus.CREATED).body("Issue was saved");
    }
}
