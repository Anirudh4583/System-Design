import java.util.*;

public class Board {
    private static final int BOARD_SIZE = 100;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board() {
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        initTransformers();
    }

    public void initTransformers() {
        // add the logic of placing snakes and ladders
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int getPositionAfterSnake(int position) {
        for (Snake s : snakes) {
            if (s.getStart() == position) {
                return s.getEnd();
            }
        }
        return -1;
    }

    public int getPositionAfterLadder(int position) {
        for (Ladder l : ladders) {
            if (l.getStart() == position)
                return l.getEnd();
        }
        return -1;
    }
}
