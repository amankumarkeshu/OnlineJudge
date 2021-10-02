package com.projects.onlinejudge.Controller;

import com.projects.onlinejudge.dto.RunRequest;
import com.projects.onlinejudge.dto.RunResponse;
import com.projects.onlinejudge.helpers.IRunner;
import com.projects.onlinejudge.helpers.LanguageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submit")
public class SubmissionController {

    @Autowired
    LanguageFactory languageFactory;
    @PostMapping("/run")
    public RunResponse validate(@RequestBody RunRequest attribute) throws Exception {

        System.out.println("CodeUrl :" +attribute.getCodeUrl());
        System.out.println("User ID  :" +attribute.getUserId());
        System.out.println("Submission ID :" +attribute.getSubmissionId());
        System.out.println("User Language :" +attribute.getLang());

        IRunner runnerFactory = languageFactory.getLangRunner(attribute.getLang());
        RunResponse runResponse = runnerFactory.runTests(attribute);
        return runResponse;
    }
}
