import java.net.URL;
import java.time.LocalDateTime;

public class UserBuilder {
    public final String userId;
    public String username;
    public String email;
    public String bio = "";
    public URL profilePictureUrl;
    public LocalDateTime createdAt = LocalDateTime.now();
    public LocalDateTime lastLogin = LocalDateTime.now();

    public UserBuilder(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public UserBuilder bio(String bio) {
        this.bio = bio;
        return this;
    }

    public UserBuilder profilePictureUrl(URL url) {
        this.profilePictureUrl = url;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
