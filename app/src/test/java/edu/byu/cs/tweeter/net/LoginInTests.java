package edu.byu.cs.tweeter.net;

import android.util.Log;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;

public class LoginInTests {

    private LoginService loginService = LoginService.getInstance();
    private LoginRequest request;
    private LoginResponse response;

    @AfterEach
    void cleanUp(){
        request = null;
        response = null;

        loginService.setCurrentUser(null);
        loginService.setLoggedInUser(null);
    }

    @Test
    void testBasicLoginTestWithTestUser(){
        request = new LoginRequest("@TestUser", "password");
        response = loginService.authenticateUser(request);

        Assertions.assertFalse(response.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), request.getUsername());

    }

    @Test
    void testInvalidUserCredentials(){
        request = new LoginRequest("NotValid", "password");
        response = loginService.authenticateUser(request);

        Assertions.assertTrue(response.isError());
        Assertions.assertNull(loginService.getCurrentUser());
    }

    @Test
    void testInvalidPassword(){
        request = new LoginRequest("@TestUser", "notValid");
        response = loginService.authenticateUser(request);

        Assertions.assertTrue(response.isError());
        Assertions.assertNull(loginService.getCurrentUser());
    }

    @Test
    void testLoginUserThatJustSignedUp(){
        SignUpRequest signUpRequest = new SignUpRequest("Username", "password", "Test", "Me", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);

        Assertions.assertFalse(signUpResponse.isError());

        Assertions.assertNotNull(loginService.getCurrentUser());
        Assertions.assertNotNull(loginService.getLoggedInUser());

        User signedInUser = loginService.getLoggedInUser();

        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);

        request = new LoginRequest("@Username", "password");
        response = loginService.authenticateUser(request);

        Assertions.assertFalse(response.isError());
        Assertions.assertNotNull(loginService.getLoggedInUser());
        Assertions.assertNotNull(loginService.getCurrentUser());
        Assertions.assertEquals(loginService.getCurrentUser(), signedInUser);
    }
}
