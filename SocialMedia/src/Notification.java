import java.time.LocalDateTime;

public class Notification {
    private final String notificationId;
    private final String message;
    private final String sourceId;
    private final NotificationType type;
    private final LocalDateTime createdAt;
    private boolean isRead;

    public Notification(String notificationId, NotificationType type, String message, String sourceId) {
        this.notificationId = notificationId;
        this.message = message;
        this.sourceId = sourceId;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }

    // getter setter
    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getSourceId() {
        return sourceId;
    }

    public boolean getRead() {
        return isRead;
    }

    public void markAsRead() {
        isRead = true;
    }
}
