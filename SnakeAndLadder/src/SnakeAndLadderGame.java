public class SnakeAndLadderGame {
    private static GameManager gameManager = new GameManager();

    public static void main(String[] args) {
        Player p1 = gameManager.createPlayer("John");
        Player p2 = gameManager.createPlayer("Henry");

        while (true) {

            gameManager.play(p1);

            if (gameManager.isGameOver())
                break;

            gameManager.play(p2);

            if (gameManager.isGameOver())
                break;
        }

    }
}
