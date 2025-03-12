import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChronologicalFeedStrategy implements FeedGenerationStrategy {
    @Override
    public List<Post> generateFeed(User user, int limit, int offset) {
        // return DB.getAllPosts().stream().filter(post ->
        // user.getFollowing().contains(post.getAuthorId) ||
        // post.getAuthodId().equals(user.getId())).sort(Comparator.comparing(Post::getCreatedAt()).reversed()).limit(limit).offset(offset).collect(Collectors.toList());
        return new ArrayList<>();
    }
}
