package edu.byu.cs.client.net;

import android.view.View;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.model.services.SignUpService;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.net.response.SignUpResponse;
import edu.byu.cs.client.presenter.LoginPresenter;

public class LoginInTests {

    private LoginService loginService = LoginService.getInstance();
    private LoginRequest request;
    private LoginResponse response;

    public class ViewImplementation implements LoginPresenter.View {
        @Override
        public void login(View v)
        {
            //Called in View
        }

        @Override
        public void signUp(View v)
        {
            //Called in View
        }
    }

    private LoginPresenter presenter = new LoginPresenter(new ViewImplementation());



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
        response = presenter.loginUser(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), request.getUsername());

    }

    @Test
    void testInvalidUserCredentials(){
        request = new LoginRequest("NotValid", "password");
        response = presenter.loginUser(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(presenter.getCurrentUser());
    }

    @Test
    void testInvalidPassword(){
        request = new LoginRequest("@TestUser", "notValid");
        response = presenter.loginUser(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(presenter.getCurrentUser());
    }

    @Test
    void testLoginUserThatJustSignedUp(){
        SignUpRequest signUpRequest = new SignUpRequest("Username5", "password", "Test", "Me", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);

        Assertions.assertTrue(signUpResponse.isSuccess());

        Assertions.assertNotNull(presenter.getCurrentUser());
        Assertions.assertNotNull(presenter.getLoggedInUser());

        User signedInUser = presenter.getLoggedInUser();

        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);

        request = new LoginRequest("@Username5", "password");
        response = presenter.loginUser(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(presenter.getLoggedInUser());
        Assertions.assertNotNull(presenter.getCurrentUser());
        Assertions.assertEquals(presenter.getCurrentUser(), signedInUser);
    }
}
