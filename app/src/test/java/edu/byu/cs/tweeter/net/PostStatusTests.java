package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.PostService;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.PostResponse;

public class PostStatusTests {
    private PostResponse response;
    private PostService service = PostService.getInstance();
    private LoginService loginService = LoginService.getInstance();

    @Test
    void simplePost(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = service.postStatus("Test Status");

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void checkFeedsForStatus() {
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = service.postStatus("Test Status2");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(loginService.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"Test Status2");
    }

    @Test
    void testUserMentions(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = service.postStatus("@test @mytestuser @moretests");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(loginService.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"@test @mytestuser @moretests");
        Assertions.assertEquals(statusList.get(0).getUserMentions().size(), 3);
    }

    @Test
    void testLinks(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = service.postStatus("www.google.com www.test.com www.twitter.com");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(loginService.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"www.google.com www.test.com www.twitter.com");
        Assertions.assertEquals(statusList.get(0).getLinks().size(), 3);
    }

    @Test
    void testBothMentionsAndLinks(){
        LoginRequest loginRequest = new LoginRequest("@TestUser", "password");
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);

        Assertions.assertFalse(loginResponse.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), loginRequest.getUsername());

        response = service.postStatus("www.google.com @test @mytestuser @moretests www.test.com www.twitter.com");

        Assertions.assertTrue(response.isSuccess());

        List<Status> statusList = ServerFacade.getInstance().getUserStatuses().get(loginService.getLoggedInUser());

        Assertions.assertEquals(statusList.get(0).getMessage(),"www.google.com @test @mytestuser @moretests www.test.com www.twitter.com");
        Assertions.assertEquals(statusList.get(0).getLinks().size(), 3);
        Assertions.assertEquals(statusList.get(0).getUserMentions().size(), 3);
    }

}
