package com.example.cinema.controller;

import com.example.cinema.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private SessionService sessionService;

    public IndexController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sessions", sessionService.findAllSessions());
        return "index";
    }
}
