package com.hart.Supermarket.employee.api;

import com.hart.Supermarket.employee.repository.Employee;
import com.hart.Supermarket.employee.repository.EmployeeRepository;
import com.hart.Supermarket.employee.security.JwtUtil;
import com.hart.Supermarket.employee.security.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public ResponseEntity<?> createEmployee(@RequestBody Employee e) {

        for (Employee create : employeeRepository.findAll()) {
            if (e.getEmail().equals(create.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee Already Exists");
            }
        }

        e.setUuid();
        employeeRepository.save(e);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
    }

    // GET
    @GetMapping(value= "/{uuid}", produces = { "application/json" } )
    public ResponseEntity<Employee> getEmployeeByUuid(@PathVariable String uuid){
        Employee e = employeeRepository.findEmployeeByUuid(uuid);
        e.setPassword("#");
        return ResponseEntity.status(HttpStatus.FOUND).body(e);
    }

    @GetMapping(value= "/all", produces = { "application/json" } )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees) {
            e.setPassword("#");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(employees);
    }


    // PUT  -- Fix this one at a later time
    @PutMapping(value= "/{uuid}", produces = { "application/json" } )  //FIX THIS
    public ResponseEntity<Employee> updateEmployeeByUuid(@RequestBody Employee e) {
        employeeRepository.save(e);
        e.setPassword("#"); //No passwords transmitted
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
    }



    //DELETE
    @DeleteMapping(value= "/{uuid}", produces = { "application/json" } )
    public ResponseEntity<?> deleteEmployeeByUuid(@PathVariable String uuid,
                                       HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String user = principal.getName();

        Employee e = employeeRepository.findEmployeeByUuid(uuid);
        if (e.getEmail().equals(user)) {
            employeeRepository.delete(e);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted " + user);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not authorized to delete this user.");
    }

    //DELETE  - TEMPORARY
    @DeleteMapping(value= "/deleteall", produces = { "application/json" } )
    public String deleteAll(){
        employeeRepository.deleteAll();
        return "Deleted all";
    }


}
