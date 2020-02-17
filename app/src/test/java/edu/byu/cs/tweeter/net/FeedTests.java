package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.FeedService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.PostService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.request.FollowerRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.PostResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;

public class FeedTests {
    private FeedService service = FeedService.getInstance();
    private FeedRequest request;
    private FeedResponse response;
    private LoginService loginService = LoginService.getInstance();

    @Test
    void testAtLoginEveryoneHasFeed(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        Map<User, List<Status>> userFeeds = ServerFacade.getInstance().getUserFeeds();

        for (Map.Entry<User, List<Status>> entry : userFeeds.entrySet()) {
            List<Status> statusList = entry.getValue();
            if(entry.getKey().getAlias().equals("@TweeterBot")){
                Assertions.assertEquals(statusList.size(), 0);
            }
            else {
                Assertions.assertNotEquals(statusList.size(), 0);
            }
        }
    }

    @Test
    void testAtSignUpEveryoneHasFeed(){
        SignUpRequest signUpRequest = new SignUpRequest("Username1", "password", "Test", "SignUP", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);
        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username1");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertFalse(signUpResponse.isError());

        List<Status> statusList = ServerFacade.getInstance().getUserFeeds().get(signedUpUser);

        Assertions.assertNotEquals(statusList.size(), 0);

    }

    @Test
    void newPostIsOnPeoplesFeeds(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        PostResponse postResponse = PostService.getInstance().postStatus("Test Status2");
        Assertions.assertTrue(postResponse.isSuccess());

        List<User> followers = ServerFacade.getInstance().getFollowers(new FollowerRequest(loginService.getLoggedInUser(), 10000, null)).getFollowers();

        for (User follower: followers) {
            List<Status> feed = ServerFacade.getInstance().getFeed(new FeedRequest(follower, 100000, null)).getStatuses();
            Assertions.assertEquals(feed.get(0).getMessage(), "Test Status2");
        }
    }

}
