package org.exi.oauthserver.services;

import org.exi.oauthserver.dto.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public JwtResponse login(String clientId, String clientSecret){

        JwtResponse jwt = JwtResponse.builder()
                .token_type("bearer")
                .accessToken("asdfasdfasdfasdf")
                .issuedAt(System.currentTimeMillis() + "")
                .clientId(clientId)
                .expiresIn(3600)
                .build();

        return  jwt;
    }
}
