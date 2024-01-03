package com.example.controller;


import com.example.entity.Event;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/addTicket")
    public Ticket addTicket(@RequestBody Ticket event) {
        return ticketService.saveTicket(event);
    }

    @DeleteMapping("/ticket/{id}")
    public String cancelTicket(@PathVariable int id) {
        return ticketService.deleteTicketById(id);
    }

    @GetMapping("/ticket/{user}")
    public Ticket getTicketByUser(@PathVariable User user) {
        return ticketService.getTicketByUser(user);
    }

    @GetMapping("/ticket/{event}")
    public Ticket getTicketByEvent(@PathVariable Event event) {
        return ticketService.getTicketByEvent(event);
    }

    @PutMapping("/bookTicket")
    public Ticket bookTicket(@RequestBody User userId, Event eventId, int seat) {
        return ticketService.boockTicket(userId, eventId, seat);
    }

}
