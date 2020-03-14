package edu.byu.cs.client.net.request;

import edu.byu.cs.client.model.domain.Status;

public class PostRequest {
    private Status status;

    public PostRequest(Status status){
        this.status = status;
    }
}
