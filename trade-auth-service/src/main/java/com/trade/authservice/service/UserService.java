package com.trade.authservice.service;

import com.trade.authservice.model.User;

import java.util.List;

public interface UserService {

    User user(User user);

    List<User> users();

}
