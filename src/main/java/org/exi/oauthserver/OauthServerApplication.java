package org.exi.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@EnableConfigurationProperties
@SpringBootApplication
public class OauthServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(OauthServerApplication.class, args);
    }

}
