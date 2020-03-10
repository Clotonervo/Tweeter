package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.net.response.UserAliasResponse;
import edu.byu.cs.client.presenter.Presenter;

public class UserAliasTask extends AsyncTask<String, Void, UserAliasResponse> {

    private UserAliasObserver observer;
    private Presenter presenter;

    ///////// Interface //////////
    public interface UserAliasObserver {
        void userSuccess(User user);
        void userError(String error);
    }

    // ========================== Constructor ========================================
    public UserAliasTask(UserAliasObserver c, Presenter p)
    {
        presenter = p;
        observer = c;
    }

    //--****************-- Do In Background --***************--
    @Override
    protected UserAliasResponse doInBackground(String... alias)
    {
        UserAliasResponse userAliasResponse = presenter.getUserByAlias(alias[0]);
        return userAliasResponse;
    }

    //--****************-- On Post Execute --***************--
    @Override
    protected void onPostExecute(UserAliasResponse userAliasResponse)
    {
        if(userAliasResponse.isSuccess()) {
            observer.userSuccess(userAliasResponse.getUser());
        }
        else {
            observer.userError(userAliasResponse.getMessage());
        }
    }
}
