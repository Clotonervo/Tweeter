package edu.byu.cs.client.net.response;

public class IsFollowingResponse extends Response {
    private boolean isFollowing;

    public IsFollowingResponse(boolean result){
        super(true);
        isFollowing = result;
    }

    public IsFollowingResponse(String message){
        super(false, message);
        isFollowing = false;
    }

    public boolean getIsFollowing(){
        return this.isFollowing;
    }

    @Override
    public boolean isSuccess() {
        return super.isSuccess();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
