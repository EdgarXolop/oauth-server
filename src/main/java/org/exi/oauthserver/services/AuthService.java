package org.exi.oauthserver.services;

import org.exi.oauthserver.dto.JwtResponse;
import org.exi.oauthserver.model.RefreshData;
import org.exi.oauthserver.model.User;
import org.exi.oauthserver.exceptions.ApiUnauthorizedException;
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

    public JwtResponse login(String clientId, String username, String password) {

        UUID uuid = UUID.randomUUID();

        User user = User.builder()
                .uuid(uuid)
                .name("Edgar")
                .lastName("Xolop")
                .role("reader")
                .username("foobar")
                .build();

        RefreshData refreshData = RefreshData
                .builder()
                .uuid(user.getUuid())
                .build();

        JwtResponse jwt = JwtResponse.builder()
                .token_type("Bearer")
                .accessToken(jwtIO.generateToken(user))
                .issuedAt(dateUtils.getDateMillis() + "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .refreshToken(jwtIO.generateRefreshToken(jwtIO.generateRefreshToken(refreshData)))
                .build();

        return  jwt;
    }
    public JwtResponse refreshToken(String clientId, String clientSecret, String refreshToken) throws ApiUnauthorizedException{
        boolean isValid = false;
        JwtResponse jwt = null;
        isValid = !jwtIO.validateToken(refreshToken);

        if(isValid){
            UUID uuid = UUID.randomUUID();

            User user = User.builder()
                    .uuid(uuid)
                    .name("Edgar")
                    .lastName("Xolop")
                    .role("reader")
                    .username("foobar")
                    .build();

            RefreshData refreshData = RefreshData
                    .builder()
                    .uuid(user.getUuid())
                    .build();

            jwt = JwtResponse.builder()
                    .token_type("Bearer")
                    .accessToken(jwtIO.generateToken(user))
                    .issuedAt(dateUtils.getDateMillis() + "")
                    .clientId(clientId)
                    .expiresIn(EXPIRES_IN)
                    .refreshToken(jwtIO.generateRefreshToken(jwtIO.generateRefreshToken(refreshData)))
                    .build();
        }else{
            throw new ApiUnauthorizedException("Invalid refresh_token");
        }
        return jwt;
    }

    public JwtResponse clientAuth(String clientId, String clientSecret){

        JwtResponse jwt = JwtResponse.builder()
                .token_type("Bearer")
                .accessToken(jwtIO.generateToken("Default Value"))
                .issuedAt(dateUtils.getDateMillis() + "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN*10)
                .build();

        return jwt;
    }
}
