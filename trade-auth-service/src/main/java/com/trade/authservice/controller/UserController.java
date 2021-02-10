package com.trade.authservice.controller;

import com.trade.authservice.model.AuthenticationReponse;
import com.trade.authservice.model.User;
import com.trade.authservice.service.MyUserDetailsService;
import com.trade.authservice.service.UserService;
import com.trade.authservice.utility.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "User Management API", description = "Operations to manage Users")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/save")
    public User user(@RequestBody User user) {
        return userService.user(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationReponse(jwt));
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.users();
    }

}
