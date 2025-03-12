import java.time.LocalDateTime;
import java.util.*;

public abstract class Post {
    private final String postId;
    private final String authorId;
    private final String content;
    private final LocalDateTime createdAt;
    private final Set<String> likedBy;
    private final List<Comment> comments;

    private Post(PostBuilder<String> builder) {
        this.postId = builder.postId;
        this.authorId = builder.authorId;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.likedBy = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    // getters setter
    public String getPostId() {
        return postId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // like
    public Set<String> getLikes() {
        return likedBy;
    }

    public int getTotalLikes() {
        return likedBy.size();
    }

    public void likePost(String userId) {
        if (!likedBy.contains(userId))
            likedBy.add(userId);

        // notify the post author
    }

    public void unlikePost(String userId) {
        likedBy.remove(userId);
    }

    // add comment
    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public int getTotalComments() {
        return comments.size();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);

        // notify post author
    }

}
