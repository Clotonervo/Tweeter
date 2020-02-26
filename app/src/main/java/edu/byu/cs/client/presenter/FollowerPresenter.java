package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.services.FollowerService;
import edu.byu.cs.client.net.request.FollowerRequest;
import edu.byu.cs.client.net.response.FollowerResponse;

public class FollowerPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public FollowerPresenter(View view) {
        this.view = view;
    }

    public FollowerResponse getFollowers(FollowerRequest request) {
        return FollowerService.getInstance().getFollowers(request);
    }
}
