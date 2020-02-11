package edu.byu.cs.tweeter.presenter;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.net.response.FollowResponse;
import edu.byu.cs.tweeter.net.response.SignOutResponse;
import edu.byu.cs.tweeter.net.response.UnfollowResponse;

public class MainPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public MainPresenter(View view) {
        this.view = view;
    }

    public SignOutResponse signOut(){               //FIXME: Come up with a better way to do this?
        LoginService.getInstance().setCurrentUser(null);
        LoginService.getInstance().setLoggedInUser(null);
        return new SignOutResponse(true, "Logged out!");
    }

    public boolean isFollowing(Follow follow){
        return FollowingService.getInstance().isFollowing(follow);
    }

    public FollowResponse followUser(Follow follow){                //FIXME: Is this right? Can i use the follow response object here?
        return FollowingService.getInstance().followUser(follow);
    }

    public UnfollowResponse unFollowUser(Follow follow) {
        return FollowingService.getInstance().unfollowUser(follow);
    }

}
