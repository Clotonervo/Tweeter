package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.SignUpResponse;

public class SignUpService {

    private static SignUpService instance;

    public static SignUpService getInstance() {
        if(instance == null) {
            instance = new SignUpService();
        }

        return instance;
    }

    private SignUpService() {}

    public SignUpResponse authenticateUser(SignUpRequest signUpRequest){
        // TODO: Communicate with server and return data given.
        ServerFacade server = new ServerFacade();
        SignUpResponse signUpResponse = server.registerNewUser(signUpRequest);
        return signUpResponse;
    }
}
