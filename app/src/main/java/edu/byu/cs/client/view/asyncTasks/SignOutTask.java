package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.net.response.SignOutResponse;
import edu.byu.cs.client.presenter.MainPresenter;

public class SignOutTask extends AsyncTask<Void, Void, SignOutResponse> {

    private SignOutObserver observer;
    private MainPresenter presenter;

    ///////// Interface //////////
    public interface SignOutObserver {
        void signOutSuccess(String message);
        void signOutError(String message);
    }

    public SignOutTask(SignOutObserver c, MainPresenter p)
    {
        presenter = p;
        observer = c;
    }

    @Override
    protected SignOutResponse doInBackground(Void ...params)
    {
        SignOutResponse signUpResponse = presenter.signOut();
        return signUpResponse;
    }

    @Override
    protected void onPostExecute(SignOutResponse signOutResponse)
    {
        if(signOutResponse.isSuccess()){
            observer.signOutSuccess(signOutResponse.getMessage());
        }
        else {
            observer.signOutError(signOutResponse.getMessage());
        }
    }
}
