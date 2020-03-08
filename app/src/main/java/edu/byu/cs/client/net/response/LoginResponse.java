package edu.byu.cs.client.net.response;

import edu.byu.cs.client.model.domain.User;

public class LoginResponse extends Response{

    private User user;

    public LoginResponse(String message) {
        super(false, message);
        this.user = null;
    }

    public LoginResponse(User user){
        super(true, null);
        this.user = user;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}