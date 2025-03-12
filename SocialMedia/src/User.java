import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class User {
    private final String userId;
    private String username;
    private String email;
    private String bio;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private URL profilePictureURL;
    private Set<User> followers;
    private Set<User> following;
    private List<Post> posts;
    private List<Notification> notifications;

    User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.email = builder.email;
        this.bio = builder.bio;
        this.profilePictureURL = builder.profilePictureUrl;
        this.createdAt = builder.createdAt;
        this.lastLogin = builder.lastLogin;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
        this.posts = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    // getters & setters
    public String getUserId() {
        return userId;
    };

    // follow
    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }

    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }

    public void follow(User user) {
        if (user.getUserId().equals(userId)) {
            throw new RuntimeException("Can't follow yourself");
        }

        this.followers.add(user);

        // notify the user
    }

    public void unfollow(User user) {
        this.following.remove(user);
    }

    // post
    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    // notifications
    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }
}
