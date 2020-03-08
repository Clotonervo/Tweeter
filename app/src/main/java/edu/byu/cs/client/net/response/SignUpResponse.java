package edu.byu.cs.client.net.response;

import edu.byu.cs.client.model.domain.User;

public class SignUpResponse extends Response{

    private User signedInUser;

    public SignUpResponse(String message) {
        super(false,message);
    }

    public SignUpResponse(User signedInUser){
        super(false, null);
        this.signedInUser = signedInUser;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public User getUser() {
        return signedInUser;
    }
}
