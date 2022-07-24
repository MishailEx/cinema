package com.example.cinema.service;

import com.example.cinema.model.Ticket;

import java.util.Comparator;

public class TicketCellComparator implements Comparator<Ticket> {

    public int compare(Ticket a, Ticket b) {

        if (a.getCell() > b.getCell()) {
            return 1;
        } else if (a.getCell() < b.getCell()) {
            return -1;
        } else {
            return 0;
        }
    }
}
