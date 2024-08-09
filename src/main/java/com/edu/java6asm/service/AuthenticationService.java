package com.edu.java6asm.service;

import com.edu.java6asm.dto.request.AuthenticationRequest;
import com.edu.java6asm.dto.request.IntrospectRequest;
import com.edu.java6asm.dto.response.AuthenticationResponse;
import com.edu.java6asm.dto.response.IntrospectResponse;
import com.edu.java6asm.repository.UserRepository;
import com.edu.java6asm.util.JWTUtil;
import com.edu.java6asm.util.PasswordUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse authenticated(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null) {
            var result = PasswordUtil.checkPassword(request.getPassword(), user.getPassword());
            if (result) {
                JWTUtil jwtUtil = new JWTUtil();
                var token = jwtUtil.generateToken(user);
                return AuthenticationResponse.builder().authenticated(true).token(token).build();
            }
        }
        return AuthenticationResponse.builder().authenticated(false).token("").build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String signer = "EhZgs6o9HQe8iGdmD4QbRFdmVJKrMx31PCei8nnyHaKQCbx3g2kk9flzsaVmKDUr";
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(signer.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();
    }
}
