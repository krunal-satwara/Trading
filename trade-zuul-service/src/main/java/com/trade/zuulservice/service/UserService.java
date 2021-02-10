package com.trade.zuulservice.service;

import com.trade.zuulservice.model.User;

import java.util.List;

public interface UserService {

    User user(User user);

    List<User> users();

}
