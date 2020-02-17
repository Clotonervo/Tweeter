package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.FollowerService;
import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.StoryService;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.StoryRequest;
import edu.byu.cs.tweeter.net.response.FollowingResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.StoryResponse;

public class ViewUserFollowersTest {

    private LoginService loginService = LoginService.getInstance();

    @Test
    void viewOtherUserFollowers(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        FollowingResponse response = FollowingService.getInstance().getFollowees(new FollowingRequest(loginService.getLoggedInUser(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> following = response.getFollowees();
        loginService.setCurrentUser(following.get(0));


        response = FollowingService.getInstance().getFollowees(new FollowingRequest(loginService.getCurrentUser(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> followingOtherUser = response.getFollowees();

        Assertions.assertNotEquals(following, followingOtherUser);

        for (User user: followingOtherUser) {
            Assertions.assertNotEquals(user.getAlias(), loginService.getCurrentUser());
        }
    }
}
