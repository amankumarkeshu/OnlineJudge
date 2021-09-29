package com.projects.onlinejudge.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pingController {
    @RequestMapping("/ping")
    public static String ping(){
        return "pong";
    }
}
