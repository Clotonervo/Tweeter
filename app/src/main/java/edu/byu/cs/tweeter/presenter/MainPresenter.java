package edu.byu.cs.tweeter.presenter;

import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.net.response.SignOutResponse;

public class MainPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public MainPresenter(View view) {
        this.view = view;
    }

    public SignOutResponse signOut(){               //FIXME: Come up with a better way to do this?
        LoginService.getInstance().setCurrentUser(null);
        LoginService.getInstance().setLoggedInUser(null);
        return new SignOutResponse(true, "Logged out!");
    }

}
