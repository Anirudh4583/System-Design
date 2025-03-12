// Strategy pattern for feed generation

import java.util.List;

public interface FeedGenerationStrategy {
    List<Post> generateFeed(User user, int limit, int offset);
}
