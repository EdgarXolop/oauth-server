package org.exi.oauthserver.validator;

import org.exi.oauthserver.exceptions.ApiUnauthorizedException;
import org.exi.oauthserver.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Component
public class AuthValidator {

    public void validate(MultiValueMap<String,String> paramMap, String grantType) throws ApiUnauthorizedException{

        switch (grantType){
            case Constants.CLIENT_CREDENTIALS:
                if(Objects.isNull(paramMap) ||
                        Objects.isNull(paramMap.getFirst("client_id")) ||
                        Objects.isNull(paramMap.getFirst("client_secret")) ||
                        paramMap.getFirst("client_id").isEmpty() ||
                        paramMap.getFirst("client_secret").isEmpty())
                    message("The field 'client_id'/'client_secret' is invalid.");
                break;
            case Constants.REFRESH_TOKEN:
                if(Objects.isNull(paramMap))
                    message("The fields are invalid.");
                else if(Objects.isNull(paramMap.getFirst("client_id")) ||
                        paramMap.getFirst("client_id").isEmpty())
                    message("The field 'client_id' is invalid.");
                else if (Objects.isNull(paramMap.getFirst("client_secret")) ||
                        paramMap.getFirst("client_secret").isEmpty())
                    message("The field 'client_secret' is invalid.");
                else if (Objects.isNull(paramMap.getFirst("refresh_token")) ||
                        paramMap.getFirst("refresh_token").isEmpty())
                    message("The field 'refresh_token' is invalid.");
                break;
            case Constants.PASSWORD:
                if(Objects.isNull(paramMap))
                    message("The fields is invalid.");
                else if(Objects.isNull(paramMap.getFirst("client_id")) ||
                        paramMap.getFirst("client_id").isEmpty())
                    message("The field 'client_id' is invalid.");
                else if(Objects.isNull(paramMap.getFirst("username")) ||
                        paramMap.getFirst("username").isEmpty())
                    message("The field 'username' is invalid.");
                else if(Objects.isNull(paramMap.getFirst("password")) ||
                        paramMap.getFirst("password").isEmpty())
                    message("The field 'password' is invalid.");
                break;
            default:
                message("The filed 'grand_type' is invalid.");
                break;
        }

    }

    private void message(String message) throws ApiUnauthorizedException{
        throw  new ApiUnauthorizedException(message);
    }
}
