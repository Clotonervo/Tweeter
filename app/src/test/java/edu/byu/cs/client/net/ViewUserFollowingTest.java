package edu.byu.cs.client.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.net.request.FollowingRequest;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.response.FollowingResponse;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.presenter.FollowingPresenter;

public class ViewUserFollowingTest {

    private LoginService loginService = LoginService.getInstance();

    public class ViewImplementation implements FollowingPresenter.View {

    }

    private FollowingPresenter presenter = new FollowingPresenter(new ViewImplementation());


    @Test
    void viewOtherUserFollowings(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertTrue(loginResponse.isSuccess());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        FollowingResponse response = presenter.getFollowing(new FollowingRequest(presenter.getLoggedInUser().getAlias(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> following = response.getFollowees();
        loginService.setCurrentUser(following.get(0));


        response = presenter.getFollowing(new FollowingRequest(presenter.getCurrentUser().getAlias(), 1000, null));
        Assertions.assertTrue(response.isSuccess());

        List<User> followingOtherUser = response.getFollowees();

        Assertions.assertNotEquals(following, followingOtherUser);

        for (User user: followingOtherUser) {
            Assertions.assertNotEquals(user.getAlias(), presenter.getCurrentUser());
        }
    }
}
