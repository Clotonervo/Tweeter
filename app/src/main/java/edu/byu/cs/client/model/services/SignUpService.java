package edu.byu.cs.client.model.services;

import java.io.IOException;
import java.net.URL;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.response.SignUpResponse;

public class SignUpService {

    private static SignUpService instance;
    private final ServerFacade serverFacade;
    private static final String URL_PATH = "/signup";


    public static SignUpService getInstance() {
        if(instance == null) {
            instance = new SignUpService();
        }

        return instance;
    }

    private SignUpService() {serverFacade = ServerFacade.getInstance();}

    public SignUpResponse authenticateUser(SignUpRequest signUpRequest){
        try {
            SignUpResponse signUpResponse = serverFacade.registerNewUser(signUpRequest, URL_PATH);
            return signUpResponse;
        }
        catch (IOException x){
            return new SignUpResponse(x.getMessage());
        }
    }
}
