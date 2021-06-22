package com.hart.Supermarket.employee.security;

import com.hart.Supermarket.employee.repository.Employee;
import com.hart.Supermarket.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    final Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    //Turn off authentication for a specific endpoint
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/employees/login");
        web.ignoring().antMatchers("/employees/all"); //temporary
        web.ignoring().antMatchers("/employees/create");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees) {
            auth.inMemoryAuthentication()
                    .withUser(e.getEmail())
                    .password(e.getPassword())
                    .roles(e.getRoles());
        }
    }

    //Will use a separate hashing
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//
//        List<Employee> employees = employeeRepository.findAll();
//        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
//
//        for (Employee e : employees) {
//            UserDetails user =
//                    User.withDefaultPasswordEncoder() //Will use different hashing.
//                            .username(e.getEmail())
//                            .password(e.getPassword())
//                            .roles("USER")
//                            .build();
//            users.add(user);
//        }
//
//        return new InMemoryUserDetailsManager(users);
//    }



}