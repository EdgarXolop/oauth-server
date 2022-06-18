package org.exi.oauthserver.dao;

public interface IClientDAO {

    boolean find(String clientId, String clientSecret);
}
