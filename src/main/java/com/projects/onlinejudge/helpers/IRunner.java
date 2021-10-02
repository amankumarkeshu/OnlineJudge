package com.projects.onlinejudge.helpers;

import com.projects.onlinejudge.dto.RunRequest;
import com.projects.onlinejudge.dto.RunResponse;

public interface IRunner {
    public abstract RunResponse runTests(RunRequest attribute);
}
