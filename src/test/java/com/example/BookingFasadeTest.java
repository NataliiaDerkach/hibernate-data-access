package com.example;

import com.example.controller.EventController;
import com.example.controller.TicketController;
import com.example.controller.UserAccountController;
import com.example.controller.UserController;
import com.example.entity.Event;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.entity.UserAccount;
import org.assertj.core.api.BigDecimalAssert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SpringBootCrudApplication.class)
public class BookingFasadeTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);


    @Autowired
    TicketController ticketController;

    @Autowired
    EventController eventController;

    @Autowired
    UserController userController;

    @Autowired
    UserAccountController userAccountController;

    @Test
    public void bookTicketForUser(){
        User userForBook= new User(4, "Book", "wouldLikeTicket@test");
        Event event = new Event(4, "Concert", "Terrace Centr", BigDecimal.valueOf(300));
        UserAccount userAccount = new UserAccount(1, userForBook, BigDecimal.valueOf(500.0));

        userController.addUser(userForBook);
        eventController.addEvent(event);
        userAccountController.addUserAccount(userAccount);

        BigDecimal balance1= userAccountController.getUserBalanceById(userForBook);
        logger.info("Balance is: " + balance1);
        Ticket bookedTicket=ticketController.bookTicket(userForBook, event, 777);

        BigDecimal balance2= userAccountController.getUserBalanceById(userForBook);
        logger.info("Now Balance is: " + balance2);
        assertNotNull(bookedTicket, "Booked ticket should exist");

       // assertThat(balance2).isEqualByComparingTo(balance1.subtract(BigDecimal.valueOf(300)));
        assertThat(balance2).isEqualByComparingTo(balance1.subtract(event.getTicketPrice()));


    }
}
