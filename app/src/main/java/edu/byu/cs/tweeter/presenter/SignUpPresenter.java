package edu.byu.cs.tweeter.presenter;

import android.view.View;

import edu.byu.cs.tweeter.model.services.SignUpService;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.response.SignUpResponse;

public class SignUpPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
        void register(android.view.View v);
    }

    public SignUpPresenter(View view) {
        this.view = view;
    }

    public SignUpResponse signUpUser(SignUpRequest signUpRequest){
        SignUpResponse signUpResponse = SignUpService.getInstance().authenticateUser(signUpRequest);
        return signUpResponse;
    }
}
