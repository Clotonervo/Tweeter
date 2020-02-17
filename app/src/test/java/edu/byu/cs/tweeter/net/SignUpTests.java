package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.SignUpResponse;
import edu.byu.cs.tweeter.presenter.SignUpPresenter;

public class SignUpTests {

    private SignUpService service = SignUpService.getInstance();
    private LoginService loginService = LoginService.getInstance();
    private SignUpRequest request;
    private SignUpResponse response;

    @AfterEach
    void cleanUp(){
        request = null;
        response = null;

        loginService.setCurrentUser(null);
        loginService.setLoggedInUser(null);
    }

    @Test
    void testSignUpNewUser(){
        request = new SignUpRequest("Username", "password", "Test", "SignUP", null);
        response = service.authenticateUser(request);
        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username");

        Assertions.assertNotNull(signedUpUser);
        Assertions.assertFalse(response.isError());

    }

    @Test
    void testSignUpAndLogIn(){
        request = new SignUpRequest("Username2", "password", "Test", "SignUP", null);
        response = service.authenticateUser(request);

        Assertions.assertFalse(response.isError());

        Assertions.assertNotNull(loginService.getLoggedInUser());
        Assertions.assertNotNull(loginService.getCurrentUser());
        Assertions.assertEquals(loginService.getCurrentUser().getAlias(), "@Username2");
    }


    @Test
    void testSignUpWithErrors(){
        request = new SignUpRequest("Username3", null, "Test", "SignUP", null);
        response = service.authenticateUser(request);
        Assertions.assertTrue(response.isError());

        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username3");
        Assertions.assertNull(signedUpUser);
    }

    @Test
    void signUpAlreadyExistingUser(){
//        request = new SignUpRequest("Username", "password", "Test", "SignUP", null);
//        response = service.authenticateUser(request);
//
//        Assertions.assertFalse(response.isError());
//        Assertions.assertNotNull(signedUpUser);

        request = new SignUpRequest("Username", "password", "Test", "SignUP", null);
        response = service.authenticateUser(request);

        Assertions.assertTrue(response.isError());
        User signedUpUser = ServerFacade.getInstance().aliasToUser("@Username");
        Assertions.assertNotNull(signedUpUser);
        Assertions.assertEquals(signedUpUser.getAlias(), "@Username");
    }
}
