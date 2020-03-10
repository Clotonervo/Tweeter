package edu.byu.cs.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.client.R;
import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.presenter.SearchPresenter;
import edu.byu.cs.client.view.asyncTasks.UserAliasTask;

public class SearchActivity extends AppCompatActivity implements SearchPresenter.View, UserAliasTask.UserAliasObserver {

    private Button searchButton;
    private EditText searchUser;
    private SearchPresenter presenter;
    private SearchActivity instance = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_search_activity);

        presenter = new SearchPresenter(this);

        searchButton = findViewById(R.id.searchButton);
        searchUser = findViewById(R.id.userSearchText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String userAlias = searchUser.getText().toString();
                UserAliasTask userAliasTask = new UserAliasTask(instance, presenter);
                userAliasTask.execute(userAlias);
            }
        });

    }

    @Override
    public void userSuccess(User user)
    {
        if(user != null){
            presenter.setCurrentUser(user);

            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        else {
            Toast.makeText(this, "That user does not exist!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void userError(String error)
    {
        Toast.makeText(this, "Something went wrong when getting the user!", Toast.LENGTH_SHORT).show();
    }
}
