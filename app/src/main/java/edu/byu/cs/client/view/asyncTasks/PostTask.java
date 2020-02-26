package edu.byu.cs.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.client.net.response.PostResponse;
import edu.byu.cs.client.presenter.PostPresenter;

public class PostTask extends AsyncTask<String, Void, PostResponse> {

    private PostObserver observer;
    private PostPresenter presenter;

    ///////// Interface //////////
    public interface PostObserver {
        void postSuccess(String message);
        void postError(String message);
    }

    public PostTask(PostObserver c, PostPresenter p)
    {
        presenter = p;
        observer = c;
    }

    @Override
    protected PostResponse doInBackground(String ...message)
    {
        PostResponse response = presenter.sendPostInfo(message[0]);
        return response;
    }

    @Override
    protected void onPostExecute(PostResponse signOutResponse)
    {
        if(signOutResponse.isSuccess()){
            observer.postSuccess(signOutResponse.getMessage());
        }
        else {
            observer.postError(signOutResponse.getMessage());
        }
    }
}
