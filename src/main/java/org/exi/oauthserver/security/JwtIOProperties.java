package org.exi.oauthserver.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "exi.jwt")
public class JwtIOProperties {

    private Security security;
    private String timezone;
    private String issuer;
    private Token token;
    private Auth auth;
    private Excluded excluded;

    @Data
    public static class Security {
        private boolean enabled;
    }

    @Data
    public static class Token {
        private Auth auth;
        private String secret;
        private int expiresin;
    }

    @Data
    public static class Auth{
        private String path;
    }

    @Data
    private  static class Excluded{
        private String path;
    }

}
