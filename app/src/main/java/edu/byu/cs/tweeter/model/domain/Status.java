package edu.byu.cs.tweeter.model.domain;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Status {

    private final User user;
    private final String message;
//    private final List<User> mentions;
    private final Timestamp timeStamp;

    public Status(User user, String message) {
        this.user = user;
        this.message = message;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }
}
