package com.major.Login.services.impl;


import com.major.Login.Constants;
import com.major.Login.Entity.CustomeUser;
import com.major.Login.Entity.Customer;
import com.major.Login.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepositories customerRepositories;

    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Customer customer = customerRepositories.findByCustomerEmail(customerEmail);
        if (Objects.isNull(customer)) {
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND);
        }
        return new CustomeUser(customer);
    }
}
