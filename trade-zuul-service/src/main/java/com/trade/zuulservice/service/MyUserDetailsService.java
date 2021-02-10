package com.trade.zuulservice.service;

import com.trade.zuulservice.model.Customer;
import com.trade.zuulservice.model.User;
import com.trade.zuulservice.repository.CustomerRepository;
import com.trade.zuulservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //User user = userRepository.findUserByName(userName);
        User user = new User();
        try{
            Customer customer = customerRepository.findByCustomerUserName(userName);
            if(customer!=null){
                user.setName(customer.getCustomerUserName());
                user.setPassword(customer.getCustomerPassword());
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new MyUserDetails(user.getName(), bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
