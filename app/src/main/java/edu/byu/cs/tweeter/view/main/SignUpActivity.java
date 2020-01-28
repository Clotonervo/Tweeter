package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpPresenter.View  {

    private SignUpPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        presenter = new SignUpPresenter(this);
    }

    public void register(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
