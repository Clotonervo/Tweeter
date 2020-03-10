package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.model.domain.Follow;
import edu.byu.cs.client.net.response.IsFollowingResponse;
import edu.byu.cs.client.presenter.MainPresenter;
import edu.byu.cs.client.presenter.Presenter;

public class IsFollowingTask extends AsyncTask<Follow, Void, IsFollowingResponse> {

    private IsFollowingObserver observer;
    private MainPresenter presenter;
    private boolean follows;

    ///////// Interface //////////
    public interface IsFollowingObserver {
        boolean resultOK(boolean result);
        void resultError(String error);
        void followResult(boolean result);
    }

    // ========================== Constructor ========================================
    public IsFollowingTask(IsFollowingObserver c, MainPresenter p, boolean follows)
    {
        presenter = p;
        observer = c;
        this.follows = follows;
    }

    //--****************-- Do In Background --***************--
    @Override
    protected IsFollowingResponse doInBackground(Follow... follow)
    {
        IsFollowingResponse isFollowingResponse = presenter.isFollowing(follow[0]);
        return isFollowingResponse;
    }

    //--****************-- On Post Execute --***************--
    @Override
    protected void onPostExecute(IsFollowingResponse isFollowingResponse)
    {
        if (isFollowingResponse.isSuccess() && follows){
            observer.followResult(isFollowingResponse.getIsFollowing());
        }
        else if (isFollowingResponse.isSuccess()){
            observer.resultOK(isFollowingResponse.getIsFollowing());
        }
        else {
            observer.resultError(isFollowingResponse.getMessage());
        }
    }
}
