package org.exi.oauthserver.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice {

    @Value("${server.error.include-stacktrace:always}")
    private String stackTrace;

    @ExceptionHandler(ApiUnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleApiUnauthorizedException(Exception e){

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ResponseEntity<ErrorResponse> response = null;

        if(stackTrace.equals("always")){
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            e.printStackTrace(printWriter);

            String stackTrace = stringWriter.toString();

            response = new ResponseEntity<>(new ErrorResponse(status,e.getMessage(),stackTrace),status);
        }else{
            response = new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
        }

        return response;
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseEntity<ErrorResponse> response = null;

        if(stackTrace.equals("always")){
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            e.printStackTrace(printWriter);

            String stackTrace = stringWriter.toString();

            response = new ResponseEntity<>(new ErrorResponse(status,e.getMessage(),stackTrace),status);
        }else{
            response = new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
        }

        return response;
    }
}
