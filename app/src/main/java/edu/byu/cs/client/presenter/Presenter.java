package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.model.services.LoginService;

public abstract class Presenter {

    public User getCurrentUser() {
        return LoginService.getInstance().getCurrentUser();
    }

    public User getLoggedInUser() {
        return LoginService.getInstance().getLoggedInUser();
    }

    public User getUserByAlias(String alias) { return LoginService.getInstance().aliasToUser(alias); }

    public void setCurrentUser(User user) {
        LoginService.getInstance().setCurrentUser(user);
    }
}
