package com.hart.Supermarket.employee.api;

import com.hart.Supermarket.employee.Secrets;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

        //find the needed user
        Employee foundEmployee = employeeRepository.findEmployeeByEmail(
                authenticationRequest.getUsername());

        //compare passwords
        if (foundEmployee.getPassword().equals(
                authenticationRequest.getPassword())) {

            //generate a random numbered code
            String twoFactorCode = GetHex();

            //send that code to the requesting users email (temporary hardcoded email)
            sendEmailForTwoFactorAuthentication("hart87@gmail.com", twoFactorCode);

            //update the users twoFactorString field in DB
            foundEmployee.setTwoFactorString(twoFactorCode);
            employeeRepository.save(foundEmployee);

            return ResponseEntity.ok("First Factor Authenticated.");
        }

        return (ResponseEntity) ResponseEntity.badRequest();

    }

    private  String GetHex() {
        Random random = new Random();
        Integer num = random.nextInt(9000) + 1000;
        String result = Integer.toHexString(num);
        return result;
    }

    private void sendEmailForTwoFactorAuthentication(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Two Factor Authentication Code");
        message.setText(text);
        emailSender.send(message);
    }


    @PostMapping(value= "/login/2fa", produces = { "application/json" } )
    public ResponseEntity<?>  authenticateSecondFactor(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

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

    @GetMapping(value= "/validate", produces = { "application/json" } )
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("Successfully Validated Token");
    }

    @PostMapping(value= "/login/changePassword", produces = { "application/json" } )
    public ResponseEntity<?> changePassword(@RequestParam String secAnswer) {
        return null; //will reset the users password after the correct question.
    }

}
