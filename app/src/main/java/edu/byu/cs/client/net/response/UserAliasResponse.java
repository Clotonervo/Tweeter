package edu.byu.cs.client.net.response;

import edu.byu.cs.client.model.domain.User;

public class UserAliasResponse extends Response {
    private User user;

    public UserAliasResponse(User user){
        super(true);
        this.user = user;
    }

    public UserAliasResponse(String message){
        super(false, message);
        this.user = null;
    }

    public User getUser(){
        return this.user;
    }

    @Override
    public boolean isSuccess() {
        return super.isSuccess();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
