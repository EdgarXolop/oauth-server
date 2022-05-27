package org.exi.oauthserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorizedException extends Exception{

    public ApiUnauthorizedException(String message){
        super(message);
    }

}
