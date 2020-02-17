package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.FollowerService;
import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.net.request.FollowerRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.response.FollowerResponse;
import edu.byu.cs.tweeter.net.response.FollowingResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;

public class ViewUserFollowingTest {

    private LoginService loginService = LoginService.getInstance();

    @Test
    void viewOtherUserFollowers(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        FollowerResponse response = FollowerService.getInstance().getFollowers(new FollowerRequest(loginService.getLoggedInUser(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> followers = response.getFollowers();
        loginService.setCurrentUser(followers.get(0));


        response = FollowerService.getInstance().getFollowers(new FollowerRequest(loginService.getCurrentUser(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> followersOtherUser = response.getFollowers();

        Assertions.assertNotEquals(followers, followersOtherUser);

        for (User user: followersOtherUser) {
            Assertions.assertNotEquals(user.getAlias(), loginService.getCurrentUser());
        }
    }
}
