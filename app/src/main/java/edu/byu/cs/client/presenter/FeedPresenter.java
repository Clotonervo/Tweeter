package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.services.FeedService;
import edu.byu.cs.client.net.request.FeedRequest;
import edu.byu.cs.client.net.response.FeedResponse;

public class FeedPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public FeedPresenter(View view) {
        this.view = view;
    }

    public FeedResponse getFeed(FeedRequest request) {
        return FeedService.getInstance().getFeed(request);
    }
}
