package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;

public class FeedService {

    private static FeedService instance;

    public static FeedService getInstance() {
        if(instance == null) {
            instance = new FeedService();
        }

        return instance;
    }

    private FeedService() {}

    public FeedResponse getFeed(FeedRequest feedRequest) {
        // TODO: Communicate with server and return data given.
        ServerFacade server = new ServerFacade();
        FeedResponse feedResponse = server.getFeed(feedRequest);
        return feedResponse;
    }

}
