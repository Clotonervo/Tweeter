package edu.byu.cs.client.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.net.request.FollowingRequest;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.request.StoryRequest;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.net.response.StoryResponse;
import edu.byu.cs.client.presenter.StoryPresenter;

public class ViewUserStoryTests {
    private LoginService loginService = LoginService.getInstance();


    public class ViewImplementation implements StoryPresenter.View {

    }

    private StoryPresenter presenter = new StoryPresenter(new ViewImplementation());

    @Test
    void viewOtherUserStory(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertTrue(loginResponse.isSuccess());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        List<User> following = ServerFacade.getInstance().getFollowing(new FollowingRequest(presenter.getLoggedInUser().getAlias(), 1000, null)).getFollowees();
        StoryResponse response = presenter.getStory(new StoryRequest(presenter.getLoggedInUser(), 10, null));
        Assertions.assertFalse(response.isError());
        List<Status> storyUserLoggedIn = response.getStatusList();

        loginService.setCurrentUser(following.get(0));

        response = presenter.getStory(new StoryRequest(presenter.getCurrentUser(), 10, null));
        Assertions.assertFalse(response.isError());
        List<Status> storyOtherUser = response.getStatusList();

        Assertions.assertNotEquals(storyOtherUser, storyUserLoggedIn);

        for (Status status: storyOtherUser) {
            Assertions.assertEquals(status.getUser(), presenter.getCurrentUser());
        }
    }
}
