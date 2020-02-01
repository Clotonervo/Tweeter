package edu.byu.cs.tweeter.model.domain;

public class Status {

    private final User user;
    private final String message;

    public Status(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
