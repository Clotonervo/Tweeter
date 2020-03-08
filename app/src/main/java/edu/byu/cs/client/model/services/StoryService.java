package edu.byu.cs.client.model.services;

import java.io.IOException;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.StoryRequest;
import edu.byu.cs.client.net.response.StoryResponse;

public class StoryService {

    private static StoryService instance;
    private final ServerFacade serverFacade;
    private static final String URL_PATH = "/story";



    public static StoryService getInstance() {
        if(instance == null) {
            instance = new StoryService();
        }

        return instance;
    }

    private StoryService() { serverFacade = ServerFacade.getInstance(); }

    public StoryResponse getStory(StoryRequest storyRequest){
        try {
            StoryResponse signUpResponse = serverFacade.getStory(storyRequest, URL_PATH);
            return signUpResponse;
        }
        catch (IOException x){
            return new StoryResponse(x.getMessage());
        }
    }
}
