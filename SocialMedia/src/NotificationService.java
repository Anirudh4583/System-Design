import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Observer pattern

public class NotificationService {

    private static NotificationService instance;
    private final Map<String, User> userObservers = new ConcurrentHashMap<>();

    private NotificationService() {
    }

    public static synchronized NotificationService getInstance() {
        if (instance == null)
            instance = new NotificationService();
        return instance;
    }

    public void registerUser(User user) {
        userObservers.put(user.getUserId(), user);
    }

    public void unregisterUser(User user) {
        userObservers.remove(user.getUserId());
    }

    public void notify(User user, Notification notification) {
        user.addNotification(notification);
    }

    public void broadcastNotification(Set<String> userIds, Notification notification) {
        userIds.forEach(u -> {
            User user = userObservers.get(u);
            if (user != null)
                user.addNotification(notification);
        });
    }
}
