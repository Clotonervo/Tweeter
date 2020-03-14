package edu.byu.cs.client.net;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import edu.byu.cs.client.model.domain.Follow;
import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.net.request.FeedRequest;
import edu.byu.cs.client.net.request.FollowerRequest;
import edu.byu.cs.client.net.request.FollowingRequest;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.request.PostRequest;
import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.request.StoryRequest;
import edu.byu.cs.client.net.request.UserAliasRequest;
import edu.byu.cs.client.net.response.FeedResponse;
import edu.byu.cs.client.net.response.FollowResponse;
import edu.byu.cs.client.net.response.FollowerResponse;
import edu.byu.cs.client.net.response.FollowingResponse;
import edu.byu.cs.client.net.response.IsFollowingResponse;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.net.response.PostResponse;
import edu.byu.cs.client.net.response.SignOutResponse;
import edu.byu.cs.client.net.response.SignUpResponse;
import edu.byu.cs.client.net.response.StoryResponse;
import edu.byu.cs.client.net.response.UnfollowResponse;
import edu.byu.cs.client.net.response.UserAliasResponse;

public class ServerFacade {

    private static ServerFacade instance;
    private static final String SERVER_URL = "https://myy7ktcr13.execute-api.us-east-2.amazonaws.com/Beta";
    private Map<String, String> headers;

    /*
        Constructors
     */
    public static ServerFacade getInstance() {
        if(instance == null) {
            instance = new ServerFacade();
        }

        return instance;
    }

    public void setAuthToken(String authToken){
        headers.put("Authorization", authToken);
    }

    public void deleteAuthToken(){
        headers.remove("Authorization");
    }

    private ServerFacade(){
        headers = new TreeMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("accept", "application/json");
    }

    /*
             --------------------- Get Followers

  */

    public FollowerResponse getFollowers(FollowerRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, FollowerResponse.class);
    }

    /*
                 --------------------- get Following

      */
    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FollowingResponse getFollowees(FollowingRequest request, String urlPath) throws IOException
    {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, FollowingResponse.class);
    }

        /*
             --------------------- Login User

  */
        public LoginResponse authenticateUser(LoginRequest request, String urlPath) throws IOException {
            ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
            return clientCommunicator.doPost(urlPath, request, headers, LoginResponse.class);
        }


    /*
                --------------------- Sign Up User

     */

    public SignUpResponse registerNewUser(SignUpRequest request, String urlPath) throws IOException{
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, SignUpResponse.class);
    }

    /*
             --------------------- Get Story

  */
    public StoryResponse getStory(StoryRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, StoryResponse.class);
    }


    /*
             --------------------- Get Feed

  */

    public FeedResponse getFeed(FeedRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, FeedResponse.class);
    }

    /*
             --------------------- Post Status

  */

    public PostResponse post(Status postedStatus, String urlPath) throws IOException, RuntimeException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        PostRequest postRequest = new PostRequest(postedStatus);
        return clientCommunicator.doPost(urlPath, postRequest, headers, PostResponse.class);
    }



    /*
                 --------------------- Get user from Alias

      */

    public UserAliasResponse aliasToUser(UserAliasRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, headers, UserAliasResponse.class);
    }

    /*
             --------------------- isFollowing

  */
    public IsFollowingResponse isFollowing(Follow follow, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        IsFollowingResponse isFollowingResponse = clientCommunicator.doPost(urlPath, follow, headers, IsFollowingResponse.class);
        return isFollowingResponse;
    }

    /*
             --------------------- followUser

  */

    public FollowResponse followUser(Follow follow, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, follow, headers, FollowResponse.class);
    }


    /*
             --------------------- unfollowUser

  */

    public UnfollowResponse unfollowUser(Follow follow, String urlPath) throws IOException{
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, follow, headers, UnfollowResponse.class);
    }

    /*
             --------------------- sign out User
     */
    public SignOutResponse signOutUser(String userAlias, String urlPath) throws IOException{
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, userAlias, headers, SignOutResponse.class);
    }
}

//TODO: Test EVERYTHING
