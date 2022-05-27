package org.exi.oauthserver.services;

import org.apache.catalina.User;
import org.exi.oauthserver.dto.JwtResponse;
import org.exi.oauthserver.dto.UserDTO;
import org.exi.oauthserver.security.JwtIO;
import org.exi.oauthserver.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtIO;
    @Autowired
    private DateUtils dateUtils;
    @Value("${exi.jwt.token.expires-in:3600}")
    private int EXPIRES_IN;
    public JwtResponse login(String clientId, String clientSecret) {

        UUID uuid = UUID.randomUUID();

        UserDTO user = UserDTO.builder()
                .uid("122296")
                .name("Edgar")
                .lastName("Xolop")
                .role("reader")
                .username("foobar")
                .build();

        JwtResponse jwt = JwtResponse.builder()
                .token_type("Bearer")
                .accessToken(jwtIO.generateToken(user))
                .issuedAt(dateUtils.getDateMillis() + "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return  jwt;
    }
}
