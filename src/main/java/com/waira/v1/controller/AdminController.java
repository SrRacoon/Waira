package com.waira.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/Dashboard")
    public String adminPage() {
        return "dashboard-admin"; // Return the name of the admin view (admin.html)
    }
}
