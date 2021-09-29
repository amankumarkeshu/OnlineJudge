package com.projects.onlinejudge.dto;

import java.io.Serializable;

public class RunResponse implements Serializable {
    Integer passed;
    Integer failed;

    public RunResponse(Integer passed, Integer failed) {
        this.passed = passed;
        this.failed = failed;
    }

    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }
}
