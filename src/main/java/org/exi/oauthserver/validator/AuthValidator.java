package org.exi.oauthserver.validator;

import org.exi.oauthserver.exceptions.ApiUnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Component
public class AuthValidator {

    private static final  String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String,String> paramMap, String grantType) throws ApiUnauthorizedException{

        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)){
            message("The filed 'grand_type' is invalid.");
        }
        if(Objects.isNull(paramMap) ||
                Objects.isNull(paramMap.getFirst("client_id")) ||
                Objects.isNull(paramMap.getFirst("client_secret")) ||
                paramMap.getFirst("client_id").isEmpty() ||
                paramMap.getFirst("client_secret").isEmpty()){
            message("The field 'client_id'/'client_secret' is invalid.");
        }
    }

    private void message(String message) throws ApiUnauthorizedException{
        throw  new ApiUnauthorizedException(message);
    }
}
