package edu.byu.cs.client.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.model.services.PostService;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.net.response.PostResponse;
import edu.byu.cs.client.presenter.PostPresenter;

public class PostStatusTests {
    private PostResponse response;
    private PostService service = PostService.getInstance();
    private LoginService loginService = LoginService.getInstance();

    public class ViewImplementation implements PostPresenter.View {

    }

    private PostPresenter presenter = new PostPresenter(new ViewImplementation());

    @Test
    void simplePost(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = presenter.sendPostInfo("Test Status");

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void checkFeedsForStatus() {
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = presenter.sendPostInfo("Test Status2");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(presenter.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"Test Status2");
    }

    @Test
    void testUserMentions(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = presenter.sendPostInfo("@test @mytestuser @moretests");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(presenter.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"@test @mytestuser @moretests");
        Assertions.assertEquals(statusList.get(0).getUserMentions().size(), 3);
    }

    @Test
    void testLinks(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = presenter.sendPostInfo("www.google.com www.test.com www.twitter.com");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(presenter.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"www.google.com www.test.com www.twitter.com");
        Assertions.assertEquals(statusList.get(0).getLinks().size(), 3);
    }

    @Test
    void testBothMentionsAndLinks(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = presenter.sendPostInfo("www.google.com @test @mytestuser @moretests www.test.com www.twitter.com");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(presenter.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"www.google.com @test @mytestuser @moretests www.test.com www.twitter.com");
        Assertions.assertEquals(statusList.get(0).getLinks().size(), 3);
        Assertions.assertEquals(statusList.get(0).getUserMentions().size(), 3);
    }

}
