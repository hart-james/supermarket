package com.hart.Supermarket.employee.api;

import com.hart.Supermarket.employee.repository.EmployeeRepository;
import com.hart.Supermarket.employee.security.JwtUtil;
import com.hart.Supermarket.employee.security.MyUserDetailService;
import com.hart.Supermarket.employee.security.models.AuthenticationRequest;
import com.hart.Supermarket.employee.security.models.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees/authentication")
public class AuthenticationController {

    final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JavaMailSender emailSender;




    // POST
    @PostMapping(value= "/login", produces = { "application/json" } )
    public ResponseEntity<?> authenticateFirstFactor(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@hart.com");
        message.setTo("hart87@gmail.com");
        message.setSubject("login");
        message.setText("2 factor auth");
        emailSender.send(message);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
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

    @PostMapping(value= "/login/2fa", produces = { "application/json" } )
    public String authenticateSecondFactor(@RequestParam String code) {
        return null; //will return the Authorization string
    }

    @GetMapping(value= "/validate", produces = { "application/json" } )
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("Successfully Validated Token");
    }

    @PostMapping(value= "/login/changePassword", produces = { "application/json" } )
    public ResponseEntity<?> changePassword(@RequestParam String secAnswer) {
        return null; //will reset the users password after the correct question.
    }

}
