package com.trade.authservice.service;

import com.trade.authservice.model.User;
import com.trade.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User user(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> users() {
        return userRepository.findAll();
    }

}
