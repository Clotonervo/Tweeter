package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.net.request.SignUpRequest;
import edu.byu.cs.client.net.response.SignUpResponse;
import edu.byu.cs.client.presenter.SignUpPresenter;


public class SignUpTask extends AsyncTask<SignUpRequest, Void, SignUpResponse> {

    private SignUpObserver observer;
    private SignUpPresenter presenter;

    ///////// Interface //////////
    public interface SignUpObserver {
        void signUpSuccess(String message);
        void signUpError(String message);
    }

    public SignUpTask(SignUpObserver c, SignUpPresenter p)
    {
        presenter = p;
        observer = c;
    }

    @Override
    protected SignUpResponse doInBackground(SignUpRequest... signUpRequests)
    {
        SignUpResponse signUpResponse = presenter.signUpUser(signUpRequests[0]);
        return signUpResponse;
    }

    @Override
    protected void onPostExecute(SignUpResponse signUpResponse)
    {
        if(signUpResponse.isError()){
            observer.signUpError(signUpResponse.getMessage());
        }
        else {
            observer.signUpSuccess(signUpResponse.getMessage());
        }
    }
}

