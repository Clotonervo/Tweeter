package edu.byu.cs.client.model.services;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.FollowerRequest;
import edu.byu.cs.client.net.response.FollowerResponse;

public class FollowerService {

    private static FollowerService instance;

    private final ServerFacade serverFacade;

    public static FollowerService getInstance() {
        if(instance == null) {
            instance = new FollowerService();
        }

        return instance;
    }

    private FollowerService() {
        serverFacade = ServerFacade.getInstance();
    }

    public FollowerResponse getFollowers(FollowerRequest request) {
        return serverFacade.getFollowers(request);
    }
}

