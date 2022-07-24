package com.example.cinema.service;

import com.example.cinema.model.Session;
import com.example.cinema.repository.SessionRepository;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionService {
    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAllSessions() {
        List<Session> s = StreamSupport.stream(sessionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return s;
    }

    public Session findById(int id) {
        return sessionRepository.findById(id).get();
    }
}
