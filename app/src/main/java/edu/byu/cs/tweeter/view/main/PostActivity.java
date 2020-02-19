package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.PostPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.PostTask;

public class PostActivity extends AppCompatActivity implements PostPresenter.View, PostTask.PostObserver {

    private Button postButton;
    private EditText postMessage;

    PostPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_popup);

        presenter = new PostPresenter(this);

        postButton = findViewById(R.id.postButton);
        postMessage = findViewById(R.id.postMessage);


    }

    @Override
    public void postSuccess(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void postError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void postStatus(View v){
        PostTask postTask = new PostTask(this, presenter);
        postTask.execute(postMessage.getText().toString());
    }
}
