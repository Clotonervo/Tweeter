package edu.byu.cs.client.net.response;

import java.util.List;

import edu.byu.cs.client.model.domain.User;

public class FollowerResponse extends PagedResponse {

    private List<User> followers;

    public FollowerResponse(String message) {
        super(false, message, false);
    }

    public FollowerResponse(List<User> followers, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }
}