package edu.byu.cs.client.model.services;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.StoryRequest;
import edu.byu.cs.client.net.response.StoryResponse;

public class StoryService {

    private static StoryService instance;
    private final ServerFacade serverFacade;


    public static StoryService getInstance() {
        if(instance == null) {
            instance = new StoryService();
        }

        return instance;
    }

    private StoryService() { serverFacade = ServerFacade.getInstance(); }

    public StoryResponse getStory(StoryRequest storyRequest){
        StoryResponse signUpResponse = serverFacade.getStory(storyRequest);
        return signUpResponse;
    }
}
