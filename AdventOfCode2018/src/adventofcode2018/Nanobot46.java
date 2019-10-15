package adventofcode2018;

/**
 *
 * @author Lukas Thöni
 */
public class Nanobot46 {

    private final int x, y, z, range;

    public Nanobot46(int x, int y, int z, int range) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.range = range;
    }

    /**
     * Get the corners (center point plus and minus range, in each coordinate
     * direction) of this nanobot as an array of coordinate arrays.
     *
     * @return Array of six int arrays containing the coordinates of each
     * corner, in the format of {x, y, z}. The array of points is arranged in
     * the same order, being {-x, +x, -y, +y, -z, +z} from the center
     * respectively.
     */
    public int[][] getCorners() {
        int[][] corners = new int[6][3];
        corners[0] = new int[]{x - range, y, z};
        corners[1] = new int[]{x + range, y, z};
        corners[2] = new int[]{x, y - range, z};
        corners[3] = new int[]{x, y + range, z};
        corners[4] = new int[]{x, y, z - range};
        corners[5] = new int[]{x, y, z + range};
        return corners;
    }

    /**
     * Get the Manhattan distance from the center of this nanobot and the given
     * point.
     *
     * This is calculated as the number of steps required on the coordinate grid
     * to go from one point to the other, disregarding non-orthogonal movement.
     * Mathematically, it is the sum of the absolute values of the difference of
     * each coordinate, i.e. sum of abs(x1 - x2) for each coordinate.
     *
     * @param point The point to which to calculate distance.
     * @return Manhattan distance between given point and center of this
     * nanobot.
     */
    public int getManhattanDistanceToPoint(int[] point) {
        return Math.abs(x - point[0]) + Math.abs(y - point[1]) + Math.abs(z - point[2]);
    }

    /**
     * Returns true if the given point's Manhattan distance to this nanobot's
     * center is less than the nanobot's range.
     *
     * @param point The point to compare to this bot.
     * @return True if point is in range; False otherwise.
     */
    public boolean pointIsInRange(int[] point) {
        return getManhattanDistanceToPoint(point) <= range;
    }
}
