package com.example.controller;

import com.example.entity.User;
import com.example.entity.UserAccount;
import com.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/addUserAccount")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return userAccountService.saveUserAccount(userAccount);
    }

    @GetMapping("/userAccount")
    public UserAccount getUserAccountById(@PathVariable User user) {
        return userAccountService.getUserAccountById(user);
    }

    @PutMapping("/updateUserAccount")
    public UserAccount updateUserAccountBalance(@RequestBody User user, BigDecimal amount) {
        return userAccountService.updateBalance(user, amount);
    }

    @GetMapping("/balance/{user}")
    public BigDecimal getUserBalanceById(@PathVariable User user) {
        return userAccountService.getUserBalance(user);
    }
}
