package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.FollowerRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.FollowResponse;
import edu.byu.cs.tweeter.net.response.FollowerResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;

public class FollowTest {
    private FollowResponse response;
    private FollowingService service = FollowingService.getInstance();
    private LoginService loginService = LoginService.getInstance();

    @Test
    void testFollow(){
        SignUpRequest signUpRequest = new SignUpRequest("Username6", "password", "Test", "SignUP", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);
        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username6");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertFalse(signUpResponse.isError());


        List<User> following = ServerFacade.getInstance().getFollowing(new FollowingRequest(loginService.getLoggedInUser(), 1000, null)).getFollowees();

        Assertions.assertEquals(following.size(), 1);
        loginService.setCurrentUser(ServerFacade.getInstance().aliasToUser("@TestUser"));

        response = service.followUser(new Follow(loginService.getLoggedInUser(), loginService.getCurrentUser()));

        Assertions.assertTrue(response.isSuccess());
        following = ServerFacade.getInstance().getFollowing(new FollowingRequest(loginService.getLoggedInUser(), 1000, null)).getFollowees();

        Assertions.assertEquals(following.size(), 2);
        Assertions.assertEquals(following.get(1), loginService.getCurrentUser());

        List<User> followers = ServerFacade.getInstance().getFollowers(new FollowerRequest(loginService.getCurrentUser(), 1000, null)).getFollowers();

        Assertions.assertTrue(followers.contains(loginService.getLoggedInUser()));
    }




}
