package com.example;


import com.example.controller.UserController;
import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SpringBootCrudApplication.class)
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    UserController userController;

    @Test
    public void addUser() {
        User user3 = new User(3, "Huge", "h_g@test.com");

        userController.addUser(user3);
        User createdUser = userController.getUserById(user3.getId());

        assertNotNull(createdUser, "User should not be null");
        assertEquals(user3.getId(), createdUser.getId(), "User ID should match");
        assertEquals(user3.getName(), createdUser.getName(), "User name should match");
        assertEquals(user3.getEmail(), createdUser.getEmail(), "User email should match");
        logger.info("User created successfully: " + user3);
    }
}
