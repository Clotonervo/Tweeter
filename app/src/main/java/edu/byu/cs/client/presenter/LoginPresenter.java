package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.response.LoginResponse;

public class LoginPresenter extends Presenter {
    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
        void login(android.view.View v);
        void signUp(android.view.View v);
    }

    public LoginPresenter(View view) {
        this.view = view;
    }

    public LoginResponse loginUser(LoginRequest loginRequest){
        LoginResponse loginResponse = LoginService.getInstance().authenticateUser(loginRequest);
        return loginResponse;
    }
}
