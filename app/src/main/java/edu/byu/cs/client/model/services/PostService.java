package edu.byu.cs.client.model.services;

import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.net.ServerFacade;
import edu.byu.cs.client.net.response.PostResponse;

public class PostService {

    private static PostService instance;
    private final ServerFacade serverFacade;


    public static PostService getInstance() {
        if(instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    private PostService() {serverFacade = ServerFacade.getInstance();}

    public PostResponse postStatus(String postedStatus){
        Status status = new Status(LoginService.getInstance().getCurrentUser(), postedStatus);
        PostResponse postResponse = serverFacade.post(status);
        return postResponse;
    }
}
