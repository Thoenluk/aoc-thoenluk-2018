package adventofcode2018;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Thöni
 */
public class Unit29 implements Comparable {

    public static final char ELF = 'E', GOBLIN = 'G';

    public static int distanceFromPointToPoint(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }

    private int y, x, health;
    private final char type;
    private final int attackPower;

    public Unit29(int y, int x, char type) {
        this(y, x, type, 3);
    }

    public Unit29(int y, int x, char type, int attackPower) {
        this.y = y;
        this.x = x;
        this.type = type;
        this.health = 200;
        this.attackPower = attackPower;
    }

    public void attack(Unit29 unit) {
        if (null != unit) {
            unit.health -= this.attackPower;
        }
    }

    public void move(int[] direction) {
        y += direction[0];
        x += direction[1];
    }

    public boolean isInRangeOf(Unit29 unit) {
        return this.distanceFromThisToUnit(unit) <= 1;
    }

    public int distanceFromThisToUnit(Unit29 unit) {
        return distanceFromThisToPoint(unit.y, unit.x);
    }

    public int distanceFromThisToPoint(int y, int x) {
        return distanceFromPointToPoint(this.y, this.x, y, x);
    }

    protected List<int[]> findClosestPointsAmong(List<int[]> points) {
        List<int[]> closestPoints = new ArrayList<>();
        int minimumDistance = Integer.MAX_VALUE;
        int currentDistance = 0;
        for (int[] point : points) {
            currentDistance = distanceFromThisToPoint(point[0], point[1]);
            if (currentDistance < minimumDistance) {
                closestPoints.clear();
                minimumDistance = currentDistance;
            }
            //Note that this clause will return true if the above clause did.
            if (currentDistance == minimumDistance) {
                closestPoints.add(point);
            }
        }
        return closestPoints;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getPosition() {
        return new int[]{y, x};
    }

    public int getHealth() {
        return health;
    }

    public char getType() {
        return type;
    }

    public char getEnemyType() {
        return this.type == ELF ? GOBLIN : ELF;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Unit29)) {
            throw new UnsupportedOperationException("Object is not a unit.");
        } else {
            Unit29 other = (Unit29) o;
            if (this == other) {
                return 0;
            }
            if (this.y < other.y) {
                return -1;
            } else if (this.y == other.y && this.x < other.x) {
                return -1;
            } else if (this.y == other.y && this.x == other.x) {
                return 0;
            } else {
                return 1;
            }
        }
    }

}
