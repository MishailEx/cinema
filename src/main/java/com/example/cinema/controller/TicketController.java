package com.example.cinema.controller;

import com.example.cinema.service.SessionService;
import com.example.cinema.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketController {
    private TicketService ticketService;
    private SessionService sessionService;

    public TicketController(TicketService ticketService, SessionService sessionService) {
        this.ticketService = ticketService;
        this.sessionService = sessionService;
    }

    @GetMapping("/tickets/{id}")
    public String showTicket(@PathVariable("id") int id, Model model) {
        model.addAttribute("tickets", ticketService.findTicketBySession(id));
        model.addAttribute("sessionName", sessionService.findById(id));
        return "hall";
    }
}
