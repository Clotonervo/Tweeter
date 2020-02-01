package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.StoryRequest;
import edu.byu.cs.tweeter.net.response.StoryResponse;

public class StoryService {

    private static StoryService instance;

    public static StoryService getInstance() {
        if(instance == null) {
            instance = new StoryService();
        }

        return instance;
    }

    private StoryService() {}

    public StoryResponse getStory(StoryRequest storyRequest){
        // TODO: Communicate with server and return data given.
        ServerFacade server = new ServerFacade();
        StoryResponse signUpResponse = server.getStory(storyRequest);
        return signUpResponse;
    }
}
