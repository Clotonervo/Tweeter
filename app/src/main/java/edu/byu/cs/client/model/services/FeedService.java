package edu.byu.cs.client.model.services;

import java.io.IOException;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.FeedRequest;
import edu.byu.cs.client.net.response.FeedResponse;

public class FeedService {

    private static FeedService instance;
    private final ServerFacade serverFacade;
    private static final String URL_PATH = "/feed";



    public static FeedService getInstance() {
        if(instance == null) {
            instance = new FeedService();
        }

        return instance;
    }

    private FeedService() { serverFacade = ServerFacade.getInstance(); }

    public FeedResponse getFeed(FeedRequest feedRequest) {
        try {
            FeedResponse response = serverFacade.getFeed(feedRequest, URL_PATH);
            return response;
        }
        catch (Exception x){
            return new FeedResponse(x.getMessage());
        }
    }

}
