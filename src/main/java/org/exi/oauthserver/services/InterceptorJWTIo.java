package org.exi.oauthserver.services;

import org.exi.oauthserver.security.JwtIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class InterceptorJWTIo implements HandlerInterceptor {

    @Value("${exi.jwt.token.auth.path}")
    private String AUTH_PATH;
    @Value("#{'${exi.jwt.excluded.path}'.split(',')}")
    private List<String> excluded;

    private final String AUTH_HEADER = "Authorization";
    private final String TOKEN_TYPE = "Bearer";

    @Autowired
    private JwtIO jwtIO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isValid = false;

        String uri = request.getRequestURI();

        if(uri.equals(AUTH_PATH) || excluded(uri))
            isValid = true;

        if(!isValid && request.getHeader(AUTH_HEADER) != null && !request.getHeader(AUTH_HEADER).isEmpty()){
            String token = request.getHeader(AUTH_HEADER).replace(TOKEN_TYPE, "");

            isValid = !jwtIO.validateToken(token);
        }

        if(!isValid){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return isValid;
    }

    private boolean excluded(String path){
        boolean result = false;

        for(String ex : excluded){
            if(ex.equals("#") || ex.equals(path)){
                result = true;
                break;
            }
        }

        return result;
    }
}
