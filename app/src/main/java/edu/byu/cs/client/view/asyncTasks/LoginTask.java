package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.net.response.LoginResponse;
import edu.byu.cs.client.presenter.LoginPresenter;

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResponse> {

    private LoginObserver observer;
    private LoginPresenter presenter;

    ///////// Interface //////////
    public interface LoginObserver {
        void loginSuccess(String message);
        void loginError(String error);
    }

    // ========================== Constructor ========================================
    public LoginTask(LoginObserver c, LoginPresenter p)
    {
        presenter = p;
        observer = c;
    }

    //--****************-- Do In Background --***************--
    @Override
    protected LoginResponse doInBackground(LoginRequest... logReqs)
    {
        LoginResponse loginResponse = presenter.loginUser(logReqs[0]);
        return loginResponse;
    }

    //--****************-- On Post Execute --***************--
    @Override
    protected void onPostExecute(LoginResponse loginResponse)
    {
        if(loginResponse.isError()) {
            observer.loginError(loginResponse.getMessage());
        }
        else {
            observer.loginSuccess(loginResponse.getMessage());
        }
    }
}
