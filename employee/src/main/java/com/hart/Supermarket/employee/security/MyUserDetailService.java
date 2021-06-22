package com.hart.Supermarket.employee.security;

import com.hart.Supermarket.employee.repository.Employee;
import com.hart.Supermarket.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee e = employeeRepository.findEmployeeByEmail(email);


        return new User(e.getEmail(), e.getPassword(),
                new ArrayList<>());
    }
}