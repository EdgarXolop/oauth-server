package org.exi.oauthserver.dao;

public class ClientDAOImpl implements IClientDAO{

    public static final ClientDAOImpl instance = new ClientDAOImpl();

    private ClientDAOImpl(){}

    public static ClientDAOImpl getInstance(){
        return instance;
    }

    @Override
    public boolean find(String clientId, String clientSecret) {
        return true;
    }
}
