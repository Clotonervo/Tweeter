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
import edu.byu.cs.client.net.response.SignOutResponse;
import edu.byu.cs.client.net.response.SignUpResponse;
import edu.byu.cs.client.presenter.MainPresenter;

public class SignOutTests {
    private SignOutResponse response;
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


    @AfterEach
    void cleanUp(){
        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);
    }

    @Test
    void loginAndLogout() {
        LoginRequest request = new LoginRequest("@TestUser", "password");
        LoginResponse response = loginService.authenticateUser(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(presenter.getCurrentUser().getAlias(), request.getUsername());

        loginService.setCurrentUser(null);
        loginService.setLoggedInUser(null);

        Assertions.assertNull(presenter.getCurrentUser());
        Assertions.assertNull(presenter.getLoggedInUser());
    }

    @Test
    void signUpAndLogout(){
        SignUpRequest request = new SignUpRequest("Username9", "password", "Test", "User", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(request);
        User signedUpUser = presenter.getUserByAlias("@Username9");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertTrue(signUpResponse.isSuccess());

        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);

        signedUpUser = presenter.getUserByAlias("@Username9");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertNull(presenter.getCurrentUser());
        Assertions.assertNull(presenter.getLoggedInUser());
    }

}
