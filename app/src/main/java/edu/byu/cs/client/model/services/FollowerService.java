package edu.byu.cs.client.model.services;

import java.io.IOException;

import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.request.FollowerRequest;
import edu.byu.cs.client.net.response.FollowerResponse;

public class FollowerService {

    private static FollowerService instance;
    private static final String URL_PATH = "/followers";

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
        try {
            return serverFacade.getFollowers(request, URL_PATH);
        }
        catch (IOException x){
            return new FollowerResponse(x.getMessage());
        }
    }
}

