package com.edu.java6asm.controller;

import com.edu.java6asm.dto.request.AuthenticationRequest;
import com.edu.java6asm.dto.request.IntrospectRequest;
import com.edu.java6asm.dto.response.AuthenticationResponse;
import com.edu.java6asm.dto.response.IntrospectResponse;
import com.edu.java6asm.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    AuthenticationResponse authentication(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticated(request);
    }

    @PostMapping("/introspect")
    IntrospectResponse introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return authenticationService.introspect(request);
    }
}
