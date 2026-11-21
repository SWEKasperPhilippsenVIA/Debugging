import java.awt.*;
import java.util.Random;

public class Board {
    private static final int BATTLESHIP_LENGTH = 3;
    private final boolean[][] grid;
    private int hitCount = 0;

    public Board(int gridSize) {
        grid = new boolean[gridSize][gridSize];
        addBattleship();
    }

    public boolean attack(Point attackPoint) {
        if (grid[attackPoint.x][attackPoint.y]) {
            hitCount++;
            return true;
        }
        return false;
    }

    public boolean isSunk() {
        return hitCount >= BATTLESHIP_LENGTH;
    }

    public void reveal() {
        System.out.println("Revealing battleship location");
        StringBuilder gridVisualisation = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (grid[x][y]) {
                    gridVisualisation.append("x ");
                } else {
                    gridVisualisation.append("o ");
                }
            }
            gridVisualisation.append(System.lineSeparator());
        }
        System.out.println(gridVisualisation);
    }

    public int getGridSize() {
        return grid.length;
    }

    private void addBattleship() {
        // Vælg retning først (0 = vandret, 1 = lodret), så startpunktet kan vælges,
        // så hele skibet altid er inden for griddet.
        boolean vertical = getRandomInteger(1) == 1;
        int maxX = vertical ? grid.length - 1 : grid.length - BATTLESHIP_LENGTH;
        int maxY = vertical ? grid.length - BATTLESHIP_LENGTH : grid.length - 1;

        Point startPoint = new Point(getRandomInteger(maxX), getRandomInteger(maxY));
        Point endPoint = vertical ? getVerticalEndPoint(startPoint) : getHorizontalEndPoint(startPoint);

        for (int x = startPoint.x; x < endPoint.x + 1; x++) {
            for (int y = startPoint.y; y < endPoint.y + 1; y++) {
                grid[x][y] = true;
            }
        }
    }

    private Point getHorizontalEndPoint(Point startPoint) {
        return new Point(startPoint.x + BATTLESHIP_LENGTH - 1, startPoint.y);
    }

    private Point getVerticalEndPoint(Point startPoint) {
        return new Point(startPoint.x, startPoint.y + BATTLESHIP_LENGTH - 1);
    }

    // Returnerer et tilfældigt tal fra 0 til og med maxValue.
    private static int getRandomInteger(int maxValue) {
        return new Random().nextInt(maxValue + 1);
    }
}
