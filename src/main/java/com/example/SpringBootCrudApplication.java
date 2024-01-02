package com.example;


import com.example.controller.UserController;
import com.example.entity.Event;
import com.example.entity.User;
import com.example.service.EventService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class SpringBootCrudApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserController userController;

    @Autowired
    EventService eventService;

    public static void main(String[] args) {

        SpringApplication.run(SpringBootCrudApplication.class, args);



    }

    @Override
    public void run(String... args) throws Exception {




        //event dao test
//
//        Event event = new Event(1, "Football", "Football place", BigDecimal.valueOf(250));
//        Event event2 = new Event(2, "Dance", "City plaza", BigDecimal.valueOf(50));
//        Event event3 = new Event(3, "Swim", "CPool centre", BigDecimal.valueOf(120));
//        eventService.createEvent(event3);
//
//        List<Event> existingEventsInDB = eventService.getEvents();
//        System.out.println("Found following events: " + existingEventsInDB);


    }


}