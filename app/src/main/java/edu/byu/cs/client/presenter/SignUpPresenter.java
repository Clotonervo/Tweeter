package edu.byu.cs.client.presenter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.byu.cs.client.model.services.SignUpService;
import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.response.SignUpResponse;

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
        SignUpService service = SignUpService.getInstance();
        SignUpResponse signUpResponse = service.authenticateUser(signUpRequest);
        return signUpResponse;
    }
}
