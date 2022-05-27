package org.exi.oauthserver.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserDTO implements Serializable {

    private String uid;
    private String name;
    private String lastName;
    private String role;
    private String username;
}
