package com.waira.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// SOLO ES TEMPORAL MIENTRAS SE HACEN LAS DEMÁS VISTAS
@Controller
public class TemporalController {
    @GetMapping("/temporal")
    public String explore() {
        return "temporal";
    }
}
