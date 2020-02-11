package edu.byu.cs.tweeter.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.LoginService;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.request.FollowerRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.LoginRequest;
import edu.byu.cs.tweeter.net.request.SignUpRequest;
import edu.byu.cs.tweeter.net.request.StoryRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;
import edu.byu.cs.tweeter.net.response.FollowResponse;
import edu.byu.cs.tweeter.net.response.FollowerResponse;
import edu.byu.cs.tweeter.net.response.FollowingResponse;
import edu.byu.cs.tweeter.net.response.LoginResponse;
import edu.byu.cs.tweeter.net.response.PostResponse;
import edu.byu.cs.tweeter.net.response.SignUpResponse;
import edu.byu.cs.tweeter.net.response.StoryResponse;
import edu.byu.cs.tweeter.net.response.UnfollowResponse;

public class ServerFacade {

    private static Map<User, List<User>> followeesByFollower;
    private static Map<User, List<User>> followersByFollowees;

    private static List<Follow> follows;

    private static Map<User, List<Status>> userStatuses;

    public FollowerResponse getFollowers(FollowerRequest request){
        assert request.getLimit() >= 0;
        assert request.getFollower() != null;

        if(followersByFollowees == null) {
            initializeFollowers();
        }

        List<User> allFollowers = followersByFollowees.get(request.getFollower());
        List<User> responseFollowers = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            if (allFollowers != null) {
                int followeesIndex = getFolloweesStartingIndex(request.getLastFollowee(), allFollowers);

                for(int limitCounter = 0; followeesIndex < allFollowers.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                    responseFollowers.add(allFollowers.get(followeesIndex));
                }

                hasMorePages = followeesIndex < allFollowers.size();
            }
        }

        return new FollowerResponse(responseFollowers, hasMorePages);
    }


    public FollowingResponse getFollowees(FollowingRequest request) {

        assert request.getLimit() >= 0;
        assert request.getFollower() != null;

        if(followeesByFollower == null) {
            followeesByFollower = initializeFollowees();
        }
        System.out.print(followeesByFollower);

        List<User> allFollowees = followeesByFollower.get(request.getFollower());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            if (allFollowees != null) {
                int followeesIndex = getFolloweesStartingIndex(request.getLastFollowee(), allFollowees);

                for(int limitCounter = 0; followeesIndex < allFollowees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                    responseFollowees.add(allFollowees.get(followeesIndex));
                }

                hasMorePages = followeesIndex < allFollowees.size();
            }
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }

    private int getFolloweesStartingIndex(User lastFollowee, List<User> allFollowees) {

        int followeesIndex = 0;

        if(lastFollowee != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allFollowees.size(); i++) {
                if(lastFollowee.equals(allFollowees.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    followeesIndex = i + 1;
                }
            }
        }

        return followeesIndex;
    }

    /**
     * Generates the followee data.
     */
    private Map<User, List<User>> initializeFollowees() {

        Map<User, List<User>> followeesByFollower = new HashMap<>();

        follows = getFollowGenerator().generateUsersAndFollows(100,
                0, 50, FollowGenerator.Sort.FOLLOWER_FOLLOWEE);

        // Populate a map of followees, keyed by follower so we can easily handle followee requests
        for(Follow follow : follows) {
            List<User> followees = followeesByFollower.get(follow.getFollower());

            if(followees == null) {
                followees = new ArrayList<>();
                followeesByFollower.put(follow.getFollower(), followees);
            }

            followees.add(follow.getFollowee());
        }

        followersByFollowees = new HashMap<>();

        for(Follow follow : follows) {
            List<User> followers = followersByFollowees.get(follow.getFollowee());

            if(followers == null) {
                followers = new ArrayList<>();
                followersByFollowees.put(follow.getFollowee(), followers);
            }

            followers.add(follow.getFollower());
        }


        return followeesByFollower;
    }


    private Map<User, List<User>> initializeFollowers() {


        initializeFollowees();

        return followersByFollowees;
    }

    /**
     * Returns an instance of FollowGenerator that can be used to generate Follow data. This is
     * written as a separate method to allow mocking of the generator.
     *
     * @return the generator.
     */
    FollowGenerator getFollowGenerator() {
        return FollowGenerator.getInstance();
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest){

        if(!loginRequest.getUsername().equals("Test")){
            return new LoginResponse("Invalid Username", true);
        }
        else if (!loginRequest.getPassword().equals("password")){
            return new LoginResponse("Invalid Password", true);
        }
        else {
            return new LoginResponse("Login successful!", false);
        }
    }

    private Map<User, List<Status>> initializeStatuses() {

        if(followeesByFollower == null){
            followeesByFollower = initializeFollowees();
        }

        userStatuses = new HashMap<>();

        for (Map.Entry<User, List<User>> entry : followeesByFollower.entrySet()) {
            User currentUser = entry.getKey();
            List<Status> statusList = new ArrayList<>();
            for(int i = 1; i < 5; i++){
                statusList.add(new Status(currentUser, "Test status " + i));
            }
            userStatuses.put(currentUser, statusList);
        }

        return userStatuses;
    }


    public SignUpResponse registerNewUser(SignUpRequest signUpRequest){
        User signedUpUser = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
                signUpRequest.getImageURL());
        LoginService.getInstance().setCurrentUser(signedUpUser);
        LoginService.getInstance().setLoggedInUser(signedUpUser);

        return new SignUpResponse("Signed up successfully!", false);
    }

    public StoryResponse getStory(StoryRequest storyRequest){
        User user = storyRequest.getUser();

        if(userStatuses == null){
            userStatuses = initializeStatuses();
        }
        List<Status> statusList = new ArrayList<>();

        statusList = userStatuses.get(user);


        return new StoryResponse("Good stuff", statusList, false, true);
    }

    public FeedResponse getFeed(FeedRequest feedRequest){
        User user = feedRequest.getUser();
        boolean hasMorePages = true;

        if(userStatuses == null){
            userStatuses = initializeStatuses();
        }

        List<Status> statusList = new ArrayList<>();
        List<User> following = new ArrayList<>();

        following = followeesByFollower.get(user);

        for (int i = 0; i < following.size(); i++){
//            List<Status> temp = userStatuses.get(following.get(i));
            statusList.addAll(userStatuses.get(following.get(i)));

        }

        Collections.sort(statusList, new Comparator<Status>() {
            public int compare(Status o1, Status o2) {
                return o1.getTimeStamp().compareTo(o2.getTimeStamp());
            }
        });


        return new FeedResponse(true, "No Error", hasMorePages, statusList, following);

    }


    public PostResponse post(Status postedStatus){
        User user = postedStatus.getUser();

        if(userStatuses == null){
            userStatuses = initializeStatuses();
        }

        List<Status> temp = userStatuses.get(user);

        userStatuses.get(user).add(postedStatus);

        Collections.sort(userStatuses.get(user), new Comparator<Status>() {
            public int compare(Status o1, Status o2) {
                return o2.getTimeStamp().compareTo(o1.getTimeStamp());
            }
        });

        temp = userStatuses.get(user);

        return new PostResponse(true, "Everything smooth");
    }

    public User aliasToUser(String alias){
        for (Map.Entry<User, List<User>> entry : followeesByFollower.entrySet()) {
            if(entry.getKey().getAlias() == alias){
                return entry.getKey();
            }
        }
        return new User("null", "null", "null");
    }

    public boolean isFollowing(Follow follow){
        return followeesByFollower.get(follow.getFollower()).contains(follow.getFollowee());
    }

    public FollowResponse followUser(Follow follow){
        if(followeesByFollower == null){
            followeesByFollower = initializeFollowees();
        }

        if (followeesByFollower.get(follow.getFollower()).add(follow.getFollowee())){
            return new FollowResponse(true, "User successfully followed");
        }
        else{
            return new FollowResponse(false, "Something went wrong following user");
        }
    }

    public UnfollowResponse unfollowUser(Follow follow){
        if(followeesByFollower == null){
            followeesByFollower = initializeFollowees();
        }

        if (followeesByFollower.get(follow.getFollower()).add(follow.getFollowee())){
            return new UnfollowResponse(true, "User successfully unfollowed");
        }
        else{
            return new UnfollowResponse(false, "Something went wrong unfollowing user");
        }
    }
}
