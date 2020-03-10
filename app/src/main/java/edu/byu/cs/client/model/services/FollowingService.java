package edu.byu.cs.client.model.services;

import java.io.IOException;

import edu.byu.cs.client.model.domain.Follow;
import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.FollowingRequest;
import edu.byu.cs.client.net.response.FollowResponse;
import edu.byu.cs.client.net.response.FollowingResponse;
import edu.byu.cs.client.net.response.IsFollowingResponse;
import edu.byu.cs.client.net.response.UnfollowResponse;

public class FollowingService {

    private static FollowingService instance;
    private static final String URL_PATH1 = "/following";
    private static final String URL_PATH2 = "/unfollowuser";
    private static final String URL_PATH3 = "/followuser";
    private static final String URL_PATH4 = "/is-following";
    private final ServerFacade serverFacade;

    public static FollowingService getInstance() {
        if(instance == null) {
            instance = new FollowingService();
        }

        return instance;
    }

    private FollowingService() {
        serverFacade = ServerFacade.getInstance();
    }

    public FollowingResponse getFollowees(FollowingRequest request) {
        try{
            return serverFacade.getFollowees(request, URL_PATH1);
        }
        catch (IOException x){
            return new FollowingResponse("IOException caught, something went wrong");
        }
    }

    public UnfollowResponse unfollowUser(Follow follow){
        try{
            return serverFacade.unfollowUser(follow, URL_PATH2);
        }
        catch (IOException x){
            return new UnfollowResponse("IOException caught, something went wrong");
        }
    }

    public FollowResponse followUser(Follow follow){
        try{
            return serverFacade.followUser(follow, URL_PATH3);
        }
        catch (IOException x){
            return new FollowResponse(false, "IOException caught, something went wrong");
        }
    }

    public IsFollowingResponse isFollowing(Follow follow){
        try {
            return serverFacade.isFollowing(follow, URL_PATH4);
        }
        catch (IOException x){
            return new IsFollowingResponse("IOException caught, something went wrong");
        }
    }
}
