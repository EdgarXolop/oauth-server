package org.exi.oauthserver.api;

import org.exi.oauthserver.exceptions.ApiUnauthorizedException;
import org.exi.oauthserver.services.AuthService;
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

    @PostMapping(path = "oauth/client_credentials/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String,String> paramMap, @RequestParam("grant_type") String grantType) throws ApiUnauthorizedException {

        authValidator.validate(paramMap,grantType);

        return  ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }
}
