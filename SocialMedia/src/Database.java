public class Database {
    // DB stuff
    private static Database instance;

    public static synchronized Database getInstace() {
        return instance;
    }
}
