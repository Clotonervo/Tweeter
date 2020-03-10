package edu.byu.cs.client.model.services;

import java.io.IOException;
import java.net.URL;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.net.response.SignOutResponse;
import edu.byu.cs.client.net.response.UserAliasResponse;

public class LoginService {

    private static LoginService instance;
    private final ServerFacade serverFacade;
    private User currentUser;
    private User loggedInUser;
    private static final String URL_PATH = "/login";
    private static final String URL_PATH2 = "/signout";
    private static final String URL_PATH3 = "/aliastouser";


    public static LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }

        return instance;
    }

    private LoginService() {serverFacade = ServerFacade.getInstance();}

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest){
        try {
            LoginResponse loginResponse = serverFacade.authenticateUser(loginRequest, URL_PATH);

            if (!loginResponse.isSuccess()){
                return loginResponse;
            }
            else {
                currentUser = loginResponse.getUser();
                setCurrentUser(currentUser);
                setLoggedInUser(currentUser);
//                serverFacade.setAuthToken(loginResponse.getAuthToken());
                return loginResponse;
            }
        }
        catch(IOException x){
            return new LoginResponse(x.getMessage());
        }
    }

    public SignOutResponse signOutUser(){
        try {
            SignOutResponse response = serverFacade.signOutUser(loggedInUser.getAlias(), URL_PATH2);
            setCurrentUser(null);
            setLoggedInUser(null);
//            serverFacade.setAuthToken(null);
            return response;
        }
        catch (IOException x){
            return new SignOutResponse(false, x.getMessage());
        }
    }

    public UserAliasResponse aliasToUser(String alias) {
        try {
            return serverFacade.aliasToUser(alias, URL_PATH3);
        }
        catch (IOException x){
            return null;
        }
    }
}
