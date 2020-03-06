package edu.byu.cs.client.net.request;


public class FollowingRequest {

    private final String follower;
    private final int limit;
    private final String lastFollowee;

    public FollowingRequest(String follower, int limit, String lastFollowee) {
        this.follower = follower;
        this.limit = limit;
        this.lastFollowee = lastFollowee;
    }

    public String getFollower() {
        return follower;
    }

    public int getLimit() {
        return limit;
    }

    public String getLastFollowee() {
        return lastFollowee;
    }
}
