package org.exi.oauthserver.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private String clientId;
    private String clientSecret;
}
