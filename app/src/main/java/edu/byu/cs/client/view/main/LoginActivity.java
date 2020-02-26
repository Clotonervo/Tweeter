package edu.byu.cs.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.client.R;
import edu.byu.cs.client.net.request.LoginRequest;
import edu.byu.cs.client.presenter.LoginPresenter;
import edu.byu.cs.client.view.asyncTasks.LoginTask;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, LoginTask.LoginObserver {

    private EditText mUsername;
    private EditText mPassword;

    private LoginPresenter presenter;

    private Button mLoginButton;
    private Button mSignUpButton;

    //________________________ onCreate and other Fragment functions ____________________________________
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        presenter = new LoginPresenter(this);

        mUsername = this.findViewById(R.id.usernameInput);
        mPassword = this.findViewById(R.id.passwordInput);

    }

    @Override
    public void login(View v){
        LoginTask loginTask = new LoginTask(this, presenter);
        LoginRequest loginRequest = new LoginRequest(mUsername.getText().toString(), mPassword.getText().toString());
        loginTask.execute(loginRequest);
    }

    @Override
    public void signUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccess(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
