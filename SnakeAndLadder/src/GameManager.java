import java.util.*;

public class GameManager {
    private Board board;
    private List<Player> players;
    private Dice dice;

    private Boolean isGameOver = false;

    public Player createPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        return player;
    }

    public void play(Player player) {
        if (isGameOver)
            return;
        // roll the dice
        int points = dice.roll();
        System.out.println("Dice rolled! :" + points);

        int newPoisiton = player.getPosition() + points;
        int positonAfterSnake = board.getPositionAfterSnake(newPoisiton);
        int positionAfterLadder = board.getPositionAfterLadder(newPoisiton);

        if (positonAfterSnake != -1)
            newPoisiton = positonAfterSnake;
        if (positionAfterLadder != -1)
            newPoisiton = positionAfterLadder;
        // check for snake/ladder
        // move the given player to that output place on the board

        player.setPosition(newPoisiton);

        // end of each play => check for currentPlayer.getPosition ==
        // board.getBoardSize() => isGameOver = true
        if (player.getPosition() == board.getBoardSize())
            this.isGameOver = true;
    }

    public Boolean isGameOver() {
        return isGameOver;
    }
}
