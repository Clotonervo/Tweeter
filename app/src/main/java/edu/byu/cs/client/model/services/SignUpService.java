package edu.byu.cs.client.model.services;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.response.SignUpResponse;

public class SignUpService {

    private static SignUpService instance;
    private final ServerFacade serverFacade;


    public static SignUpService getInstance() {
        if(instance == null) {
            instance = new SignUpService();
        }

        return instance;
    }

    private SignUpService() {serverFacade = ServerFacade.getInstance();}

    public SignUpResponse authenticateUser(SignUpRequest signUpRequest){
        SignUpResponse signUpResponse = serverFacade.registerNewUser(signUpRequest);
        return signUpResponse;
    }
}
