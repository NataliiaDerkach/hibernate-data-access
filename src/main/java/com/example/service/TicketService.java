package com.example.service;

import com.example.dao.EventRepository;
import com.example.dao.TicketRepository;
import com.example.dao.UserAccountRepository;
import com.example.dao.UserRepository;
import com.example.entity.Event;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public String deleteTicketById(int id) {
        ticketRepository.deleteById(id);
        return "Ticket got deleted";
    }

    public Ticket getTicketByUser(User user) {
        return ticketRepository.findById(user.getId()).orElse(null);
    }

    public Ticket getTicketByEvent(Event event) {
        return ticketRepository.findById(event.getId()).orElse(null);
    }

    public Ticket addTicket(User userId, Event eventId, int seat) {

        Optional<User> optionalUser = userRepository.findById(userId.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Optional<Event> optionalEvent = eventRepository.findById(eventId.getId());
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                BigDecimal ticketPrice = event.getTicketPrice();

                Optional<UserAccount> optionalUserAccountBalance = userAccountRepository.findById(userId.getId());
                if (optionalUserAccountBalance.get().getBalance().compareTo(ticketPrice) >= 0) {

                    Ticket ticket = new Ticket();
                    ticket.setUserId(user);
                    ticket.setEventId(event);
                    ticket.setSeat(seat);

                    UserAccount userAccount = new UserAccount();
                    userAccount.setBalance(optionalUserAccountBalance.get().getBalance().subtract(ticketPrice));
                    userRepository.save(user);


                    return ticketRepository.save(ticket);
                }else {
                    throw new InsufficientFundsException("Not enough funds to book the ticket.");
                }
            } else {
                throw new EventNotFoundException("Event not found.");
            }
        } else {
            throw new UserNotFoundException("User not found.");
        }
            }
        }