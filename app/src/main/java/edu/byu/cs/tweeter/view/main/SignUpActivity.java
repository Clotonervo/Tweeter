package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.presenter.SignUpPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.SignUpTask;

public class SignUpActivity extends AppCompatActivity implements SignUpPresenter.View, SignUpTask.SignUpContext {

    private SignUpPresenter presenter;

    private EditText mUsername;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        presenter = new SignUpPresenter(this);

        mUsername = this.findViewById(R.id.usernameInput);
        mPassword = this.findViewById(R.id.passwordInput);
        mFirstName = this.findViewById(R.id.firstNameInput);
        mLastName = this.findViewById(R.id.lastNameInput);
        mImage = this.findViewById(R.id.emailInput);

    }

    public void register(View v){
        SignUpTask signUpTask = new SignUpTask(this, presenter);
        SignUpRequest signUpRequest = new SignUpRequest(mUsername.getText().toString(),
                mPassword.getText().toString(),
                mFirstName.getText().toString(),
                mLastName.getText().toString(),
                "https://i.pinimg.com/236x/ed/c7/5e/edc75e41888082aa8323c725540624f5.jpg");
        signUpTask.execute(signUpRequest);


    }


    @Override
    public void onExecuteComplete(String message, Boolean error){
        System.out.println(message);
        if(error) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
