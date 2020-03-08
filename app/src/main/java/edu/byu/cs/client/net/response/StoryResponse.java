package edu.byu.cs.client.net.response;


import java.util.List;

import edu.byu.cs.client.model.domain.Status;
import edu.byu.cs.client.net.response.PagedResponse;


public class StoryResponse extends PagedResponse {

    private List<Status> statusList;

    public StoryResponse(List<Status> statusList, boolean hasMorePages) {
        super(true, null, hasMorePages);

        this.statusList = statusList;
    }

    public StoryResponse(String message){
        super(false, message, true);
        this.statusList = null;
    }

    public String getMessage()
    {
        return super.getMessage();
    }

    public List<Status> getStatusList()
    {
        return statusList;
    }

    public void setStatusList(List<Status> statusList)
    {
        this.statusList = statusList;
    }

    public boolean isSuccess(){ return super.isSuccess(); }
}
