package edu.byu.cs.client.net.response;

import java.util.List;

import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.model.domain.User;

public class FeedResponse extends PagedResponse  {
    private List<Status> statuses;

    public FeedResponse(String message){
        super(false, message, false);
    }

    public FeedResponse(boolean success, String message, boolean hasMorePages, List<Status> statuses)
    {
        super(success, message, hasMorePages);
        this.statuses = statuses;
    }

    public List<Status> getStatuses()
    {
        return statuses;
    }

    public void setStatuses(List<Status> statuses)
    {
        this.statuses = statuses;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }
}
