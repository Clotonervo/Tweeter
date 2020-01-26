package edu.byu.cs.tweeter.presenter;

public class SignUpPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public SignUpPresenter(View view) {
        this.view = view;
    }
}
