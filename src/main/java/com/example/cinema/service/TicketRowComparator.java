package com.example.cinema.service;

import com.example.cinema.model.Ticket;

import java.util.Comparator;

public class TicketRowComparator implements Comparator<Ticket> {

    public int compare(Ticket a, Ticket b) {

        if (a.getRow() > b.getRow()) {
            return 1;
        } else if (a.getRow() < b.getRow()) {
            return -1;
        } else {
            return 0;
        }
    }
}

