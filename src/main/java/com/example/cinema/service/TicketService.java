package com.example.cinema.service;

import com.example.cinema.model.Ticket;
import com.example.cinema.model.User;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public List<Ticket> findTicketBySession(int id) {
        List<Ticket> tickets = ticketRepository.findAllBySessionId(id);
        return tickets.stream().sorted(new TicketRowComparator()
                .thenComparing(new TicketCellComparator()))
                .collect(Collectors.toList());
    }

    public List<Ticket> findForBooking(String[] idsTickets) {
        return Arrays.stream(idsTickets)
                .map(id -> ticketRepository.findById(Integer.parseInt(id)).get())
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Ticket> buyTicket(String[] idsTickets, User user) {
        List<Ticket> listTickets = findForBooking(idsTickets);
        User newUser = userService.createUser(user);
        for (Ticket t : listTickets) {
            t.setUser(newUser);
            ticketRepository.save(t);
        }
        return listTickets;
    }
}
