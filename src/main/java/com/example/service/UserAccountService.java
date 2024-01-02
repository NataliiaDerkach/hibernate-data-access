package com.example.service;

import com.example.dao.UserAccountRepository;

import com.example.entity.User;
import com.example.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.findById(id).orElse(null);
    }

    public UserAccount updateBalance(User user, BigDecimal amount) {
        UserAccount oldBalance = null;
        Optional<UserAccount> optionalbalance = userAccountRepository.findById(user.getId());
        if (optionalbalance.isPresent()) {
            oldBalance = optionalbalance.get();
            oldBalance.setBalance(optionalbalance.get().getBalance().add(amount));
            userAccountRepository.save(oldBalance);
        } else {
            return new UserAccount();
        }
        return oldBalance;
    }
}
