package com.petclinic.spring.security;

import com.petclinic.spring.dto.AuthenticationRequestDto;
import com.petclinic.spring.dto.MessageResponse;
import com.petclinic.spring.dto.RegistrationRequestDto;
import com.petclinic.spring.model.User;
import com.petclinic.spring.security.jwt.JwtTokenProvider;
import com.petclinic.spring.security.jwt.JwtUser;
import com.petclinic.spring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 *Created by olga on 18.10.2020
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider,
                                        JwtUserDetailsService jwtUserDetailsService, UserService userService,
                                        BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            System.out.println("into login");
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", jwtUser.getUsername());
            response.put("id", jwtUser.getId());
            System.out.println(jwtUser.getId());


            return new ResponseEntity(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDto signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        User newUser = userService.register(signUpRequest);
        String username = newUser.getUsername();
        System.out.println(username + " register user");
        System.out.println(newUser.getRoles());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, signUpRequest.getPassword()));
        String token = jwtTokenProvider.createToken(username, newUser.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        System.out.println(newUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
