package edu.byu.cs.client.view.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.byu.cs.client.R;
import edu.byu.cs.client.model.domain.Follow;
import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.presenter.MainPresenter;
import edu.byu.cs.client.view.asyncTasks.FollowUserTask;
import edu.byu.cs.client.view.asyncTasks.IsFollowingTask;
import edu.byu.cs.client.view.asyncTasks.LoadImageTask;
import edu.byu.cs.client.view.asyncTasks.SignOutTask;
import edu.byu.cs.client.view.asyncTasks.UnfollowUserTask;
import edu.byu.cs.client.view.cache.ImageCache;

public class MainActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver, MainPresenter.View, SignOutTask.SignOutObserver, FollowUserTask.FollowUserContext, UnfollowUserTask.UnfollowUserContext, IsFollowingTask.IsFollowingObserver {

    private MainPresenter presenter;
    private User user;
    private ImageView userImageView;
    private Button signOutButton;
    private Button followButton;
    private Button userSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        user = presenter.getCurrentUser();


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        signOutButton = findViewById(R.id.signOutButton);
        followButton = findViewById(R.id.follow_toggle);
        userSearch = findViewById(R.id.goUser);

        followButton.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToPostActivity();

            }
        });

        if(user != presenter.getLoggedInUser()){
            fab.hide();
            signOutButton.setVisibility(View.INVISIBLE);
            IsFollowingTask isFollowingTask = new IsFollowingTask(this, presenter, false);
            isFollowingTask.execute(new Follow(presenter.getLoggedInUser(), presenter.getCurrentUser()));
        }
        else {
            followButton.setVisibility(View.INVISIBLE);
        }

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                signOut();
            }
        });

        userImageView = findViewById(R.id.userImage);



        // Asynchronously load the user's image
        LoadImageTask loadImageTask = new LoadImageTask(this);
        loadImageTask.execute(presenter.getCurrentUser().getImageUrl());

        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(user.getAlias());
    }


    @Override
    public void imageLoadProgressUpdated(Integer progress) {
        // We're just loading one image. No need to indicate progress.
    }

    @Override
    public void imagesLoaded(Drawable[] drawables) {
        ImageCache.getInstance().cacheImage(user, drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }

    public void goUser(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void signOut()
    {
        SignOutTask signOutTask = new SignOutTask(this, presenter);
        signOutTask.execute();
    }

    @Override
    public void goToPostActivity(){
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    @Override
    public void followUser(View v) {
        IsFollowingTask isFollowingTask = new IsFollowingTask(this, presenter, true);
        isFollowingTask.execute(new Follow(presenter.getLoggedInUser(), presenter.getCurrentUser()));
    }

    @Override
    public void signOutSuccess(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void signOutError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFollowComplete(String message, Boolean success)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnfollowComplete(String message, Boolean success)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean resultOK(boolean result)
    {
        followButton.setVisibility(View.VISIBLE);
        if (result){
            System.out.print("Logged in user is following current user");
            followButton.setText(R.string.unfollow_button);
        }
        else {
            System.out.print("Logged in user is NOT following current user");
            followButton.setText(R.string.follow_button);
        }
        return result;
    }

    @Override
    public void resultError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void followResult(boolean result)
    {
        if (result){
            UnfollowUserTask unfollowUserTask = new UnfollowUserTask(this, presenter);
            followButton.setText(R.string.follow_button);
            unfollowUserTask.execute(new Follow(presenter.getLoggedInUser(), presenter.getCurrentUser()));
            Toast.makeText(this, "User successfully unfollowed!", Toast.LENGTH_SHORT).show();

        }
        else {
            FollowUserTask followUserTask = new FollowUserTask(this, presenter);
            followButton.setText(R.string.unfollow_button);
            followUserTask.execute(new Follow(presenter.getLoggedInUser(), presenter.getCurrentUser()));
            Toast.makeText(this, "User successfully followed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        presenter.setCurrentUser(presenter.getLoggedInUser());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}