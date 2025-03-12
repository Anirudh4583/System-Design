import java.time.LocalDateTime;

public abstract class PostBuilder<T> {
    public final String postId;
    public final String authorId;
    public String content;
    public LocalDateTime createdAt;

    public PostBuilder(String postId, String authorId) {
        this.postId = postId;
        this.authorId = authorId;
        this.createdAt = LocalDateTime.now();
    }

    public PostBuilder<T> setContent(String content) {
        this.content = content;
        return this;
    }

    public abstract Post build();

}
