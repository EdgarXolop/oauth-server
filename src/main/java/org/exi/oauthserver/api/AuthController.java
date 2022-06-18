package org.exi.oauthserver.api;

import org.exi.oauthserver.exceptions.ApiUnauthorizedException;
import org.exi.oauthserver.services.AuthService;
import org.exi.oauthserver.util.Constants;
import org.exi.oauthserver.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="v1.0")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthValidator authValidator;

    @PostMapping(path = "oauth/client_credentials/access_token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String,String> paramMap, @RequestParam("grant_type") String grantType) throws ApiUnauthorizedException {

        authValidator.validate(paramMap,grantType);

        return switch (grantType){
            case Constants.PASSWORD -> ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("username"), paramMap.getFirst("password")));
            case Constants.REFRESH_TOKEN -> ResponseEntity.ok(authService.refreshToken(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret"), paramMap.getFirst("refresh_token")));
            case Constants.CLIENT_CREDENTIALS -> ResponseEntity.ok(authService.clientAuth(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
            default -> throw new ApiUnauthorizedException("Invalid grant_type");
        };
    }
}
