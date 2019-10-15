package adventofcode2018;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Lukas Thöni
 */
public class BattleController {

    protected static final int[] UP = new int[]{-1, 0}, LEFT = new int[]{0, -1}, RIGHT = new int[]{0, 1}, DOWN = new int[]{1, 0};
    protected static final List<int[]> directions = new ArrayList<>(Stream.of(UP, LEFT, RIGHT, DOWN).collect(Collectors.toList()));

    private char[][] field;
    private ArrayList<Unit29> units;
    private ArrayList<Unit29> deadUnits;

    public BattleController(char[][] field) {
        this.field = field;
        this.units = new ArrayList<>();
        this.deadUnits = new ArrayList<>();
    }

    public int executeBattleOne() {
        List<Unit29> targets;
        List<Unit29> targetsInRange;
        int numberOfRounds = 0, result = 0;

        populateUnits();

        while (battleContinues()) {
            renderField(numberOfRounds);
            units.sort(null);
            clearAndUpdateFieldWithUnits();
            deadUnits.clear();
            for (Unit29 currentUnit : units) {
                if (!deadUnits.contains(currentUnit)) {
                    targets = findTargets(currentUnit.getEnemyType());
                    targets.removeAll(deadUnits);
                    targetsInRange = findTargetsInRange(currentUnit, targets);
                    if (targetsInRange.isEmpty() && !targets.isEmpty()) {
                        this.moveUnitTowardsNearestTarget(currentUnit);
                        targetsInRange = findTargetsInRange(currentUnit, targets);
                    }
                    deadUnits.add(attackNearbyTarget(currentUnit, targetsInRange));
                    clearAndUpdateFieldWithUnits();
                }
            }
            units.removeAll(deadUnits);
            if (battleContinues()) {
                numberOfRounds++;
            }
        }
        for (Unit29 survivor : units) {
            result += survivor.getHealth();
        }
        result *= numberOfRounds;
        return result;
    }

    public int executeBattleTwo(int elfAttackPower) {
        List<Unit29> targets;
        List<Unit29> targetsInRange;
        Unit29 target;
        int numberOfRounds = 0, result = 0;

        populateUnits(elfAttackPower);

        while (battleContinues()) {
            renderField(numberOfRounds);
            units.sort(null);
            clearAndUpdateFieldWithUnits();
            for (Unit29 currentUnit : units) {
                if (!deadUnits.contains(currentUnit)) {
                    target = findAdjacentTarget(currentUnit);
                    if (null == target) {
                        this.moveUnitTowardsNearestTarget(currentUnit);
                        target = findAdjacentTarget(currentUnit);
                    }
                    currentUnit.attack(target);
                    if (null != target && target.getHealth() <= 0) {
                        deadUnits.add(target);
                    }
                    clearAndUpdateFieldWithUnits();
                }
            }

            units.removeAll(deadUnits);
            /*for (Unit29 casualty : deadUnits) {
                if (null != casualty && casualty.getType() == Unit29.ELF) {
                    return -1;
                }
            }*/
            if (battleContinues()) {
                numberOfRounds++;
            }
        }
        for (Unit29 survivor : units) {
            result += survivor.getHealth();
        }
        result *= numberOfRounds;
        return result;
    }

    private void renderPathingDebug() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new PrintWriter(new FileOutputStream(new File("output29.txt"), true)));
            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    if (field[y][x] == '#' || field[y][x] == 'G' || field[y][x] == 'E' || field[y][x] == '.') {
                        writer.print(field[y][x] + " ");
                    } else {
                        writer.print((Character.MAX_VALUE - field[y][x]) % 10 + " ");
                    }
                }
                writer.println();
            }
            writer.println();
        } catch (Exception e) {

        } finally {
            writer.close();
        }
    }

    private void renderField(int numberOfRounds) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(new File("output29.txt"), true));

            writer.println(numberOfRounds + 1);
            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    //System.out.print(field[y][x]);
                    writer.print(field[y][x]);
                }
                //System.out.println();
                writer.print("\t\t");
                for (Unit29 unit : units) {
                    if (!deadUnits.contains(unit) && unit.getY() == y) {
                        writer.print(unit.getType() + "(" + unit.getHealth() + "), ");
                    }
                }
                writer.println();
            }
            //System.out.println(numberOfRounds);
            //System.out.flush();
        } catch (Exception e) {

        } finally {
            writer.close();
        }

    }

    private void populateUnits() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == Unit29.GOBLIN || field[y][x] == Unit29.ELF) {
                    units.add(new Unit29(y, x, field[y][x]));
                }
            }
        }
    }

    private void populateUnits(int elfAttackPower) {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == Unit29.GOBLIN) {
                    units.add(new Unit29(y, x, Unit29.GOBLIN));
                } else if (field[y][x] == Unit29.ELF) {
                    units.add(new Unit29(y, x, Unit29.ELF, elfAttackPower));
                }
            }
        }
    }

    private boolean battleContinues() {
        boolean battleContinues = false;
        char firstTypeEncountered = units.get(0).getType();
        for (Unit29 unit : units) {
            if (unit.getType() != firstTypeEncountered) {
                battleContinues = true;
                break;
            }
        }
        return battleContinues;
    }

    private void clearAndUpdateFieldWithUnits() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] != '#') {
                    field[y][x] = '.';
                }
            }
        }
        for (Unit29 unit : units) {
            if (!deadUnits.contains(unit)) {
                field[unit.getY()][unit.getX()] = unit.getType();
            }
        }
    }

    /**
     *
     * @param unit
     * @param targetsInRange
     * @return If the attacked unit died, return it. Return null in all other
     * cases.
     */
    private Unit29 attackNearbyTarget(Unit29 unit, List<Unit29> targetsInRange) {
        Unit29 target = null;
        int lowestHealth = Integer.MAX_VALUE;
        for (Unit29 targetInRange : targetsInRange) {
            if (targetInRange.getHealth() < lowestHealth) {
                lowestHealth = targetInRange.getHealth();
                target = targetInRange;
            }
        }
        if (null != target) {
            unit.attack(target);
            if (target.getHealth() <= 0) {
                return target;
            }
        }
        return null;
    }

    private List<Unit29> findTargets(char targetType) {
        ArrayList<Unit29> targets = new ArrayList<>();
        for (Unit29 unit : units) {
            if (unit.getType() == targetType) {
                targets.add(unit);
            }
        }
        return targets;
    }

    private List<Unit29> findClosestTargets(Unit29 unit, List<Unit29> possibleTargets) {
        List<Unit29> closestTargets = new ArrayList<>();
        int shortestDistance = Integer.MAX_VALUE;
        int currentDistance;
        for (Unit29 target : possibleTargets) {
            currentDistance = unit.distanceFromThisToUnit(target);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestTargets.clear();
            }
            if (currentDistance == shortestDistance) {
                closestTargets.add(target);
            }
        }
        return closestTargets;
    }

    private List<Unit29> findTargetsInRange(Unit29 unit, List<Unit29> possibleTargets) {
        List<Unit29> targetsInRange = new ArrayList<>(4);
        for (Unit29 target : possibleTargets) {
            if (target.isInRangeOf(unit)) {
                targetsInRange.add(target);
            }
        }
        return targetsInRange;
    }

    private Unit29 findAdjacentTarget(Unit29 unit) {
        Unit29 target, bestTarget = null;
        int minHP = Integer.MAX_VALUE;
        for (int[] direction : directions) {
            target = findUnitByPosition(unit.getY() + direction[0], unit.getX() + direction[1]);
            if (null != target && target.getType() == unit.getEnemyType() && target.getHealth() < minHP) {
                bestTarget = target;
                minHP = target.getHealth();
            }
        }
        return bestTarget;
    }

    private Unit29 findUnitByPosition(int y, int x) {
        for (Unit29 unit : units) {
            if (unit.getY() == y && unit.getX() == x && !deadUnits.contains(unit)) {
                return unit;
            }
        }
        return null;
    }

    private Unit29 findNearestReachableEnemy(Unit29 unit) {
        char enemyType = unit.getEnemyType();
        int y, x, positionY = unit.getY(), positionX = unit.getX(), numberOfChanges = 0;
        char[][] newField;
        do {
            numberOfChanges = 0;
            newField = new char[field.length][field[0].length];
            for (y = 1; y < field.length - 1; y++) {
                for (x = 1; x < field[y].length - 1; x++) {
                    newField[y][x] = field[y][x];
                    if (field[y][x] == enemyType) {
                        for (int[] direction : directions) {
                            if (field[y + direction[0]][x + direction[1]] == 'X'
                                    || (y + direction[0] == positionY && x + direction[1] == positionX)) {
                                return this.findUnitByPosition(y, x);
                            }
                        }
                    } else if (field[y][x] == '.') {
                        for (int[] direction : directions) {
                            if (field[y + direction[0]][x + direction[1]] == 'X'
                                    || (y + direction[0] == positionY && x + direction[1] == positionX)) {
                                newField[y][x] = 'X';
                                numberOfChanges++;
                            }
                        }
                    }
                }
            }
            field = newField;
        } while (numberOfChanges > 0);
        return null;
    }

    private void moveUnitTowardsNearestTarget(Unit29 unit) {
        Unit29 target = this.findNearestReachableEnemy(unit);
        if (null == target) {
            return;
        }
        fillFieldWithDistancesFromUnit(target);
        //this.renderPathingDebug();
        this.stepUnitTowardsCurrentTarget(unit);
    }

    private void fillFieldWithDistancesFromUnit(Unit29 unit) {
        int numberOfChanges, y, x, positionY = unit.getY(), positionX = unit.getX();

        do {
            numberOfChanges = 0;
            for (y = 1; y < field.length - 1; y++) {
                for (x = 1; x < field[y].length - 1; x++) {
                    if (field[y][x] == '.' || field[y][x] == 'X' || field[y][x] > 10000) {
                        for (int[] direction : directions) {
                            if (y + direction[0] == positionY
                                    && x + direction[1] == positionX
                                    && field[y][x] != Character.MAX_VALUE) {
                                field[y][x] = Character.MAX_VALUE;
                                numberOfChanges++;
                            } else if (10000 < field[y + direction[0]][x + direction[1]]
                                    && field[y][x] < field[y + direction[0]][x + direction[1]] - 1) {
                                field[y][x] = (char) (field[y + direction[0]][x + direction[1]] - 1);
                                numberOfChanges++;
                            }
                        }
                    }
                }
            }
        } while (numberOfChanges > 0);
    }

    /**
     * Locate the best direction to step towards the given unit's current
     * closest target and move it in that direction. Requires the field to be
     * set up as per fillFieldWithDistancesFromPoint first. If the unit can't
     * reach the target, it does nothing.
     *
     * @param unit The unit to move.
     */
    private void stepUnitTowardsCurrentTarget(Unit29 unit) {
        int[] position = unit.getPosition();
        int bestStep = 0;
        int[] bestDirection = null;
        for (int[] direction : directions) {
            if (bestStep < field[position[0] + direction[0]][position[1] + direction[1]]
                    && 10000 < field[position[0] + direction[0]][position[1] + direction[1]]) {
                bestStep = field[position[0] + direction[0]][position[1] + direction[1]];
                bestDirection = direction;
            }
        }
        if (null != bestDirection) {
            unit.move(bestDirection);
        }
    }

}
