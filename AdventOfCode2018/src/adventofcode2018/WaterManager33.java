package adventofcode2018;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lukas Thöni
 */
public class WaterManager33 {

    private final static int LEFT = -1, RIGHT = 1;
    private final static Character OPEN = '.', FLOWING_WATER = '|', RESTING_WATER = '~',
            WALL = '#';

    int maxX = 0, minY = Integer.MAX_VALUE, maxY = 0;

    Character[][] field;

    public WaterManager33(List<String> input) {
        int[] currentInstruction;
        List<int[]> instructions = new ArrayList<>(input.size());
        String[] splitLine;
        for (String line : input) {
            splitLine = line.split(" ");
            currentInstruction = new int[4];
            if (splitLine[0].equals("y")) {
                currentInstruction[0] = Integer.parseInt(splitLine[1]);
                currentInstruction[1] = currentInstruction[0];
                currentInstruction[2] = Integer.parseInt(splitLine[3]);
                currentInstruction[3] = Integer.parseInt(splitLine[4]);
            } else {
                currentInstruction[0] = Integer.parseInt(splitLine[3]);
                currentInstruction[1] = Integer.parseInt(splitLine[4]);
                currentInstruction[2] = Integer.parseInt(splitLine[1]);
                currentInstruction[3] = currentInstruction[2];
            }
            maxX = maxX < currentInstruction[3] ? currentInstruction[3] : maxX;
            minY = minY > currentInstruction[0] ? currentInstruction[0] : minY;
            maxY = maxY < currentInstruction[1] ? currentInstruction[1] : maxY;
            instructions.add(currentInstruction);
        }
        field = new Character[maxY + 2][maxX + 2];
        for (int y = 0; y < maxY + 2; y++) {
            Arrays.fill(field[y], OPEN);
        }
        for (int[] instruction : instructions) {
            for (int y = instruction[0]; y <= instruction[1]; y++) {
                for (int x = instruction[2]; x <= instruction[3]; x++) {
                    field[y][x] = WALL;
                }
            }
        }
        field[0][500] = FLOWING_WATER;
    }

    public int locateWater() {
        int result = 0;
        List<int[]> pointsOfInterest = new ArrayList<>();
        List<int[]> overflowPoints;
        pointsOfInterest.add(new int[]{1, 500});
        int[] currentPoint;
        while (!pointsOfInterest.isEmpty()) {
            currentPoint = pointsOfInterest.get(0);
            pointsOfInterest.remove(currentPoint);
            currentPoint = drillDownFrom(currentPoint);
            if (currentPoint != null) {
                overflowPoints = checkForWallsFrom(currentPoint);
                pointsOfInterest.addAll(overflowPoints);
                if (overflowPoints.isEmpty()) {
                    floodFromPoint(currentPoint);
                    currentPoint[0]--;
                    pointsOfInterest.add(currentPoint);
                }
            }
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(new File("output33.txt"), true));
            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    writer.print(field[y][x]);
                }
                writer.println();
            }
        } catch (Exception e) {

        } finally {
            writer.close();
        }
        for (int y = minY; y <= maxY; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == FLOWING_WATER || field[y][x] == RESTING_WATER) {
                    result++;
                }
            }
        }
        return result;
    }

    public int locateRestingWater() {
        int result = 0;
        this.locateWater();
        for (int y = minY; y <= maxY; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == RESTING_WATER) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Extend a column of | down from the given point and including it.
     *
     * @param point The point from which to go straight down. Note that the
     * point will be overwritten with | no matter what it is.
     * @return If this hits a solid surface (~ or #), return the last point
     * written to. Otherwise, return null.
     */
    private int[] drillDownFrom(int[] point) {
        int y = point[0], x = point[1];
        field[y][x] = FLOWING_WATER;
        for (; y < maxY && field[y + 1][x] == OPEN; y++) {
            field[y + 1][x] = FLOWING_WATER;
        }
        if (field[y + 1][x] == RESTING_WATER || field[y + 1][x] == WALL) {
            return new int[]{y, x};
        } else {
            return null;
        }
    }

    /**
     * Check for walls on both sides of the point, filling the paths with | on
     * the way, and return a list containing the points WHERE WATER FLOWS OFF
     * THE EDGE.
     *
     * @param point The point from which to move left and right.
     * @return List containing coordinate tuples of all points WHERE WATER FLOWS
     * OFF THE EDGE. If a wall is met, that point is not added. Ergo, if the
     * area is enclosed on both sides by a wall, the list will be empty (but
     * non-null)
     */
    private List<int[]> checkForWallsFrom(int[] point) {
        List<int[]> overflowPoints = new ArrayList<>(2);
        int[] overflowPoint;
        for (int direction = LEFT; direction <= RIGHT; direction += 2) {
            overflowPoint = checkForWallsInDirectionFrom(point, direction);
            if (null != overflowPoint) {
                overflowPoints.add(overflowPoint);
            }
        }
        return overflowPoints;
    }

    /**
     * Check for walls on the given side of the given point, filling the paths
     * with | on the way. If this causes water to flow off the edge, return the
     * point where it does.
     *
     * @param point The point from which to check.
     * @param direction The direction to move the x coordinate in. Expected
     * values are LEFT or RIGHT (-1 and 1 respectively). Any other values cause
     * unspecified behaviour. 0 will cause an infinite loop.
     * @return If the water flows off the edge in the given direction, return
     * the point where it does. Otherwise, if the area is enclosed in this
     * direction, return null.
     */
    private int[] checkForWallsInDirectionFrom(int[] point, int direction) {
        int y = point[0], x = point[1];
        do {
            x += direction;
            if (field[y][x] == WALL) {
                return null;
            } else {
                field[y][x] = FLOWING_WATER;
            }
        } while (field[y + 1][x] == RESTING_WATER || field[y + 1][x] == WALL);
        return new int[]{y, x};
    }

    /**
     * Flood the area left and right from the given point with ~. That area must
     * be filled with | first.
     *
     * @param point The point in the area to flood.
     */
    private void floodFromPoint(int[] point) {
        int y = point[0], x = point[1];
        for (int direction = LEFT; direction <= RIGHT; direction += 2) {
            do {
                field[y][x] = RESTING_WATER;
                x += direction;
            } while (field[y][x] == FLOWING_WATER);
            x = point[1];
        }

    }

}
