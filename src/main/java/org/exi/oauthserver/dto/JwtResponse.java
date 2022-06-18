package org.exi.oauthserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {

    @JsonProperty(value = "token_type")
    private String token_type;
    @JsonProperty(defaultValue = "access_token")
    private String accessToken;
    @JsonProperty(defaultValue = "refresh_token")
    private String refreshToken;
    @JsonProperty(value = "expires_in")
    private int expiresIn;
    @JsonProperty(defaultValue = "issued_at")
    private String issuedAt;
    @JsonProperty(defaultValue = "client_id")
    private String clientId;
}
