public class Main {

    public static void main(String[] args) {
        Database db = Database.getInstace();

        // persist users in database using db instance
        // create user references using User class with the userId generated by db

        User alice = new UserBuilder("u1", "Alice", "alice@alice.com").build();
        User bob = new UserBuilder("u2", "Bob", "bob@alice.com").build();

        alice.follow(bob);
        bob.follow(alice);

        // Post alicePost = new PostBuilder<String>("p1", "u1").setContent("Hello
        // World!").build();

    }

}
