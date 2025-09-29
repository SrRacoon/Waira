package com.waira.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {
    @GetMapping("/temporal")
    public String explore() {
        return "temporal";
    }
}
