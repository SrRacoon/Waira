package com.waira.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminPage() {
        return "dashboard-admin"; // Return the name of the admin view (admin.html)
    }
}
