package com.example.cinema.controller;

import com.example.cinema.model.Ticket;
import com.example.cinema.model.User;
import com.example.cinema.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {
    private TicketService ticketService;

    public BookingController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/booking")
    public String showTicket(@RequestParam("checkboxName") String[] idsTickets,
                             @RequestParam("session")String sessionName,
                             Model model) {
        model.addAttribute("tickets", ticketService.findForBooking(idsTickets));
        model.addAttribute("user", new User());
        model.addAttribute("sessionName", sessionName);
        return "booking";
    }

    @PostMapping("/booking")
    public String bookingTicket(@RequestParam("selectTicket")String[] idsTickets,
                                @ModelAttribute ("user") User user,
                                @RequestParam("idSession") int idSession,
                                Model model) {
        List<Ticket> tickets;
        try {
            tickets = ticketService.buyTicket(idsTickets, user);
        } catch (Exception e) {
            model.addAttribute("error",
                    "какое-то из мест уже купленно, выберите другое");
            return "redirect:/tickets/" + idSession;
        }
        model.addAttribute("tickets", tickets);
        return "successfully";
    }
}
