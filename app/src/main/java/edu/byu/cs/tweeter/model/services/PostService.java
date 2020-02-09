package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.response.PostResponse;

public class PostService {

    private static PostService instance;

    public static PostService getInstance() {
        if(instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    private PostService() {}

    public PostResponse postStatus(String postedStatus){
        // TODO: Communicate with server and return data given.
        ServerFacade server = new ServerFacade();
        Status status = new Status(LoginService.getInstance().getCurrentUser(), postedStatus);
        PostResponse postResponse = server.post(status);
        return postResponse;
    }
}
