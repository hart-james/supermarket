package com.hart.Supermarket.employee.api;

import com.hart.Supermarket.employee.repository.Employee;
import com.hart.Supermarket.employee.repository.EmployeeRepository;
import com.hart.Supermarket.employee.security.JwtUtil;
import com.hart.Supermarket.employee.security.MyUserDetailService;
import com.hart.Supermarket.employee.security.models.AuthenticationRequest;
import com.hart.Supermarket.employee.security.models.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {

    final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService userDetailsService;


    // POST
    @PostMapping(value= "/create", produces = { "application/json" } )
    public Employee createEmployee(@RequestBody Employee employee) {

        for (Employee create : employeeRepository.findAll()) {
            if (employee.getEmail() == create.getEmail()) {
                logger.error("USER EXISTS");
                return null; //response entity eventually
            }
        }
        employee.setUuid();
        employeeRepository.save(employee);

        return employee;
    }

    @PostMapping(value= "/login", produces = { "application/json" } )
    public ResponseEntity<?> authenticateFirstFactor(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping(value= "/login/2fa", produces = { "application/json" } )
    public String authenticateSecondFactor(@RequestParam String code) {
        return null; //will return the Authorization string
    }

    // GET
    @GetMapping(value= "/{uuid}", produces = { "application/json" } )
    public Employee getEmployeeByUuid(@PathVariable String uuid){
        Employee e = employeeRepository.findEmployeeByUuid(uuid);
        e.setPassword("#");
        return e;
    }

    @GetMapping(value= "/all", produces = { "application/json" } )
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees) {
            e.setPassword("#");
        }
        return employees;
    }


    // PUT
    @PutMapping(value= "/{uuid}", produces = { "application/json" } )
    public Employee updateEmployeeByUuid(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        employee.setPassword("#"); //No passwords transmitted
        return employee;
    }



    //DELETE
    @DeleteMapping(value= "/{uuid}", produces = { "application/json" } )
    public String deleteEmployeeByUuid(@PathVariable String uuid){
        Employee e = employeeRepository.findEmployeeByUuid(uuid);
        employeeRepository.delete(e);
        return "Deleted";
    }

    //DELETE  - TEMPORARY
    @DeleteMapping(value= "/deleteall", produces = { "application/json" } )
    public String deleteAll(){
        employeeRepository.deleteAll();
        return "Deleted all";
    }


}
