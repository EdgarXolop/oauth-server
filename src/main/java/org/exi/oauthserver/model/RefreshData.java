package org.exi.oauthserver.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RefreshData {
    private UUID uuid;
}

