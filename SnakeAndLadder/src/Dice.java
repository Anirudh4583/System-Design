public class Dice {
    private static final int MIN = 1;
    private static final int MAX = 6;

    public int roll() {
        return (int) (Math.random() * (MAX - MIN) + MIN);
    }
}
