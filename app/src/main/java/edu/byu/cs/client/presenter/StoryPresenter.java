package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.services.StoryService;
import edu.byu.cs.client.net.request.StoryRequest;
import edu.byu.cs.client.net.response.StoryResponse;

public class StoryPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public StoryPresenter(View view) {
        this.view = view;
    }

    public StoryResponse getStory(StoryRequest storyRequest){
        return StoryService.getInstance().getStory(storyRequest);
    }
}
