package edu.byu.cs.client.net.response;

public class UnfollowResponse extends Response {

    public UnfollowResponse()
    {
        super(true);
    }

    public UnfollowResponse(String message)
    {
        super(false, message);
    }

    @Override
    public boolean isSuccess()
    {
        return super.isSuccess();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
