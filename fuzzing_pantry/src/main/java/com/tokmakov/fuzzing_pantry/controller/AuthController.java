package com.tokmakov.fuzzing_pantry.controller;

import com.tokmakov.fuzzing_pantry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam(value = "username") String userName,
                                          @RequestParam(value = "password") String password) {
        try {
            userService.authenticateUser(userName, password);
        } catch (EntityNotFoundException | AuthenticationServiceException entityNotFoundException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(entityNotFoundException.getMessage());
        }

        Instant now = Instant.now();
        long expiry = 86400L;
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(userName)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
