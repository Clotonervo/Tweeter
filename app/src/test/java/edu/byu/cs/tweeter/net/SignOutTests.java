package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.SignOutResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;

public class SignOutTests {
    private SignOutResponse response;
    private LoginService loginService = LoginService.getInstance();

    @AfterEach
    void cleanUp(){
        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);
    }

    @Test
    void loginAndLogout() {
        LoginRequest request = new LoginRequest("@TestUser", "password");
        LoginResponse response = loginService.authenticateUser(request);

        Assertions.assertFalse(response.isError());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), request.getUsername());

        loginService.setCurrentUser(null);
        loginService.setLoggedInUser(null);

        Assertions.assertNull(loginService.getCurrentUser());
        Assertions.assertNull(loginService.getLoggedInUser());
    }

    @Test
    void signUpAndLogout(){
        SignUpRequest request = new SignUpRequest("Username9", "password", "Test", "User", null);
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(request);
        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username9");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertFalse(signUpResponse.isError());

        loginService.setLoggedInUser(null);
        loginService.setCurrentUser(null);

        signedUpUser = ServerFacade.getInstance().aliasToUser("@Username9");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertNull(loginService.getCurrentUser());
        Assertions.assertNull(loginService.getLoggedInUser());
    }

}
