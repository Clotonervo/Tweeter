package edu.byu.cs.tweeter.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.SignUpRequest;

public class SignUpTests {

    @Test
    void testSignUpNewUser(){
        SignUpRequest request = new SignUpRequest("Username", "Password", "Test", "SignUP", null);

    }

    @Test
    void testSignUpAndLogIn(){

    }


}
