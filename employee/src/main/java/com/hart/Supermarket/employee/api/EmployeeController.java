package com.hart.Supermarket.employee.api;

import com.hart.Supermarket.employee.repository.Employee;
import com.hart.Supermarket.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {

    final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    // POST
    @PostMapping(value= "/create", produces = { "application/json" } )
    public Employee createEmployee(@RequestBody Employee employee) throws Exception {

        employee.setUuid();
        employeeRepository.save(employee);
        secureNewUser(employee);
        return employee;
    }

    private void secureNewUser(Employee e) throws Exception {
        AuthenticationManagerBuilder auth = null;
        auth.inMemoryAuthentication()
                .withUser(e.getEmail())
                .password(e.getPassword())
                .roles(e.getRoles());
    }


    // GET
    @GetMapping(value= "/login/first", produces = { "application/json" } )
    public String authenticateFirstFactor(@RequestParam String email,
                                          @RequestParam String password){
        return null; //will return a redirect URL to second factor
    }

    @GetMapping(value= "/login/second", produces = { "application/json" } )
    public String authenticateSecondFactor(@RequestParam String code) {
        return null; //will return the Authorization string
    }

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
