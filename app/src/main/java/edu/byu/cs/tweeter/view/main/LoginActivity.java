package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.presenter.MainPresenter;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

//    private LoginListener loginListener;
//    private TextWatcher mWatcher;
//    private RegisterRequest mRegisterRequest;
//    private LoginRequest mLoginRequest;

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
//        mLoginRequest = new LoginRequest();

//        mLoginButton = this.findViewById(R.id.loginButton);
//        mSignUpButton = this.findViewById(R.id.signUpButton);

//        mLoginButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                setContentView(R.layout.activity_main);
//            }
//        });
//
//        mSignUpButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(this, MainActivity.class);
//
//                setContentView(R.layout.sign_up_activity);
//            }
//        });

    }

    public void login(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        mUsername = v.findViewById(R.id.usernameInput);
//        mUsername.addTextChangedListener(mWatcher);
//
//        mPassword = v.findViewById(R.id.passwordInput);
//        mPassword.addTextChangedListener(mWatcher);
//
//        mFirstName = v.findViewById(R.id.firstNameInput);
//        mFirstName.addTextChangedListener(mWatcher);
//
//        mLastName = v.findViewById(R.id.lastNameInput);
//        mLastName.addTextChangedListener(mWatcher);
//
//        mEmail = v.findViewById(R.id.emailInput);
//        mEmail.addTextChangedListener(mWatcher);
//
//
//        mLoginButton = v.findViewById(R.id.loginButton);
//        mRegisterButton = v.findViewById(R.id.registerButton);
//        validate();
//
//        mLoginButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                mLoginRequest.setLoginUserName(mUsername.getText().toString());
//                mLoginRequest.setLoginPassWord(mPassword.getText().toString());
//                LoginTask loginTask = new LoginTask(mServerHost.getText().toString(),
//                        mIPAddress.getText().toString(),
//                        LoginFragment.this);
//
//                loginTask.execute(mLoginRequest);
//            }
//        });
//
//        mRegisterButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                mRegisterRequest.setUserNameID(mUsername.getText().toString());
//                mRegisterRequest.setUserEmail(mEmail.getText().toString());
//                mRegisterRequest.setUserFirstName(mFirstName.getText().toString());
//                mRegisterRequest.setUserLastName(mLastName.getText().toString());
//                mRegisterRequest.setUserPassword(mPassword.getText().toString());
//
//                RegisterTask regTask = new RegisterTask(mServerHost.getText().toString(),
//                        mIPAddress.getText().toString(),
//                        LoginFragment.this);
//
//                regTask.execute(mRegisterRequest);
//            }
//        });
//
//
//        return v;
//    }

    //--****************************-- onExecuteComplete --*******************************--
//    @Override
//    public void onExecuteComplete(String message)
//    {
//        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//        loginListener.loginComplete();
//    }
//
//    ////////// Public Interface for Tasks ////////////
//    public interface LoginListener {
//        void loginComplete();
//    }
//
//    public void setLoginListener(LoginListener logListen)
//    {
//        loginListener = logListen;
//    }
//
//    ////////////// TextWatcher //////////////
//    private class Enabler implements TextWatcher {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            validate();
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {}
//
//    }
//}
}
