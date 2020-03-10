package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.domain.Follow;
import edu.byu.cs.client.model.services.FollowingService;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.net.response.FollowResponse;
import edu.byu.cs.client.net.response.IsFollowingResponse;
import edu.byu.cs.client.net.response.SignOutResponse;
import edu.byu.cs.client.net.response.UnfollowResponse;

public class MainPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
        void signOut();
        void goToPostActivity();
        void followUser(android.view.View v);
    }

    public MainPresenter(View view) {
        this.view = view;
    }

    public SignOutResponse signOut(){
        return LoginService.getInstance().signOutUser();
    }

    public IsFollowingResponse isFollowing(Follow follow){
        return FollowingService.getInstance().isFollowing(follow);
    }

    public FollowResponse followUser(Follow follow){
        return FollowingService.getInstance().followUser(follow);
    }

    public UnfollowResponse unFollowUser(Follow follow) {
        return FollowingService.getInstance().unfollowUser(follow);
    }

}
