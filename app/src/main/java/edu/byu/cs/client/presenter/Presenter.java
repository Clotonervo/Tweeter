package edu.byu.cs.client.presenter;

import edu.byu.cs.client.model.domain.User;
import edu.byu.cs.client.model.services.LoginService;
import edu.byu.cs.client.net.response.UserAliasResponse;

public abstract class Presenter {

    public User getCurrentUser() {
        return LoginService.getInstance().getCurrentUser();
    }

    public User getLoggedInUser() {
        return LoginService.getInstance().getLoggedInUser();
    }

    public UserAliasResponse getUserByAlias(String alias) { return LoginService.getInstance().aliasToUser(alias); }

    public void setCurrentUser(User user) {
        LoginService.getInstance().setCurrentUser(user);
    }
}
