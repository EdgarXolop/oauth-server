package org.exi.oauthserver.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class User implements Serializable {

    private UUID uuid;
    private String name;
    private String lastName;
    private String role;
    private String username;
}
