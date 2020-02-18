package edu.byu.cs.tweeter.net;

import android.view.View;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.FollowerService;
import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.FollowerRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.FollowResponse;
import edu.byu.cs.tweeter.net.response.FollowerResponse;
import edu.byu.cs.tweeter.net.response.FollowingResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;
import edu.byu.cs.tweeter.presenter.FollowerPresenter;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class FollowTest {
    private FollowResponse response;
    private FollowingService service = FollowingService.getInstance();
    private LoginService loginService = LoginService.getInstance();

    public class ViewImplementation implements MainPresenter.View {
        @Override
        public void signOut()
        {

        }

        @Override
        public void goToPostActivity()
        {

        }

        @Override
        public void followUser(View v)
        {

        }
    }

    private MainPresenter presenter = new MainPresenter(new ViewImplementation());


    @Test
    void testFollow(){
        SignUpRequest signUpRequest = new SignUpRequest("Username6", "password", "Test", "SignUP", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);
        User signedUpUser = presenter.getUserByAlias("@Username6");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertFalse(signUpResponse.isError());


        List<User> following = ServerFacade.getInstance().getFollowing(new FollowingRequest(presenter.getLoggedInUser(), 1000, null)).getFollowees();

        Assertions.assertEquals(following.size(), 1);
        loginService.setCurrentUser(presenter.getUserByAlias("@TestUser"));

        response = presenter.followUser(new Follow(presenter.getLoggedInUser(), presenter.getCurrentUser()));

        Assertions.assertTrue(response.isSuccess());

        FollowingResponse followingResponse = service.getFollowees(new FollowingRequest(presenter.getLoggedInUser(), 1000, null));
        Assertions.assertTrue(followingResponse.isSuccess());
        following = followingResponse.getFollowees();

        Assertions.assertEquals(following.size(), 2);
        Assertions.assertEquals(following.get(1), presenter.getCurrentUser());

        FollowerResponse followerResponse = FollowerService.getInstance().getFollowers(new FollowerRequest(presenter.getCurrentUser(), 1000, null));
        Assertions.assertTrue(followerResponse.isSuccess());
        List<User> followers = followerResponse.getFollowers();

        Assertions.assertTrue(followers.contains(presenter.getLoggedInUser()));
    }




}
