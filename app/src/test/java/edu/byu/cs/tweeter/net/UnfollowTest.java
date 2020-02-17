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
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;
import edu.byu.cs.tweeter.net.response.UnfollowResponse;

public class UnfollowTest {
    private UnfollowResponse response;
    private FollowingService service = FollowingService.getInstance();
    private LoginService loginService = LoginService.getInstance();

    @Test
    void testUnfollow(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());


        List<User> following = ServerFacade.getInstance().getFollowing(new FollowingRequest(loginService.getLoggedInUser(), 1000, null)).getFollowees();
        int size = following.size();
        User userToUnfollow = ServerFacade.getInstance().aliasToUser(following.get(0).getAlias());

        Assertions.assertNotEquals(following.size(), 0);

        loginService.setCurrentUser(userToUnfollow);

        response = service.unfollowUser(new Follow(loginService.getLoggedInUser(), loginService.getCurrentUser()));

        Assertions.assertTrue(response.isSuccess());
        following = ServerFacade.getInstance().getFollowing(new FollowingRequest(loginService.getLoggedInUser(), 1000, null)).getFollowees();

        Assertions.assertEquals(following.size(), size - 1);
        Assertions.assertNotEquals(following.get(0), loginService.getCurrentUser());

        List<User> followers = ServerFacade.getInstance().getFollowers(new FollowerRequest(loginService.getCurrentUser(), 1000, null)).getFollowers();

        Assertions.assertFalse(followers.contains(loginService.getLoggedInUser()));

    }


}
