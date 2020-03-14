package edu.byu.cs.client.net.request;

import edu.byu.cs.client.net.response.UserAliasResponse;

public class UserAliasRequest  {
    private String userAlias;

    public UserAliasRequest(String alias){
        this.userAlias = alias;
    }
}
