package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.response.LoginResponse;

public class LoginService {

    private static LoginService instance;

    private User currentUser;

    public static LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }

        return instance;
    }

    private LoginService() {}

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest){
        // TODO: Communicate with server and return data given.
        ServerFacade server = new ServerFacade();
        LoginResponse loginResponse = server.authenticateUser(loginRequest);
        if (loginResponse.isError()){
            return loginResponse;
        }
        else {
            currentUser = new User("Test", "User",
                    "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
            setCurrentUser(currentUser);
            return loginResponse;
        }
    }
}
