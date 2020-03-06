package edu.byu.cs.client.net.request;


public class FollowerRequest {

    private String followee;
    private int limit;
    private String lastFollower;

    public FollowerRequest(String followee, int limit, String lastFollower) {
        this.followee = followee;
        this.limit = limit;
        this.lastFollower = lastFollower;
    }

    public FollowerRequest(){}

    public String getFollower() {
        return followee;
    }

    public int getLimit() {
        return limit;
    }

    public String getLastFollowee() {
        return lastFollower;
    }
}
